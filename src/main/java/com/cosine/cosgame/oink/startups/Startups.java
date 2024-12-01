package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.account.Transaction;
import com.cosine.cosgame.oink.startups.entity.CardEntity;
import com.cosine.cosgame.oink.startups.entity.EndGameEntity;
import com.cosine.cosgame.oink.startups.entity.EndRoundEntity;
import com.cosine.cosgame.oink.startups.entity.PlayerEntity;
import com.cosine.cosgame.oink.startups.entity.StartupsEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Startups {
	int round;
	int curPlayer;
	int firstPlayer;
	
	List<Card> deck;
	List<Card> discard;
	List<Player> players;
	
	HashMap<Integer, Integer> antiMonopoly;
	HashMap<Integer, Integer> shareholder;
	
	Logger logger;
	Board board;
	
	MongoDBUtil dbutil;
	
	public Document hashMapToDocument(HashMap<Integer, Integer> map) {
		Document doc = new Document();
		for (int key: map.keySet()) {
			doc.append(Integer.toString(key), map.get(key));
		}
		return doc;
	}
	
	public HashMap<Integer, Integer> documentToHashmap(Document doc) {
		HashMap<Integer, Integer> map = new HashMap<>();
		if (doc != null) {
			Set<String> keySet = doc.keySet();
			for (String s : keySet) {
				int k = Integer.parseInt(s);
				int v = doc.getInteger(s, 0);
				map.put(k, v);
			}
		}
		return map;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("round", round);
		doc.append("curPlayer", curPlayer);
		doc.append("firstPlayer", firstPlayer);
		doc.append("antiMonopoly", antiMonopoly);
		doc.append("shareholder", shareholder);
		doc.append("logs", logger.getLogs());
		doc.append("antiMonopoly", hashMapToDocument(antiMonopoly));
		doc.append("shareholder", hashMapToDocument(shareholder));
		
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		List<Document> dos = new ArrayList<>();
		for (i=0;i<discard.size();i++) {
			dos.add(discard.get(i).toDocument());
		}
		doc.append("deck", dok);
		doc.append("discard", dos);
		
		for (i=0;i<players.size();i++) {
			players.get(i).setIndex(i);
			String n = players.get(i).getName();
			n = "player-" + n;
			doc.append(n, players.get(i).toDocument());
		}
		
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		round = doc.getInteger("round", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		firstPlayer = doc.getInteger("firstPlayer", -1);
		antiMonopoly = documentToHashmap((Document) doc.get("antiMonopoly"));
		shareholder = documentToHashmap((Document) doc.get("shareholder"));
		
		
		List<String> logs = (List<String>) doc.get("logs");
		logger = new Logger(logs);
		
		int i;
		List<Document> dok = (List<Document>) doc.get("deck");
		List<Document> dos = (List<Document>) doc.get("discard");
		deck = new ArrayList<>();
		for (i=0;i<dok.size();i++) {
			deck.add(new Card(dok.get(i)));
		}
		discard = new ArrayList<>();
		for (i=0;i<dos.size();i++) {
			discard.add(new Card(dos.get(i)));
		}
		
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setStartups(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
	}
	
	public StartupsEntity toStartupsEntity(String username) {
		StartupsEntity entity = new StartupsEntity();
		entity.setCurPlayer(curPlayer);
		entity.setRound(round);
		entity.setAntiMonopoly(antiMonopoly);
		entity.setShareholder(shareholder);
		entity.setDeckSize(deck.size());
		entity.setLogs(logger.getLogs());
		
		int i,j;
		List<PlayerEntity> lp = new ArrayList<>();
		List<CardEntity> lc = new ArrayList<>();
		List<CardEntity> ls = new ArrayList<>();
		
		
		
		boolean canDraw = false;
		int phase = Consts.OFFTURN;
		int myDrawCost = 0;
		boolean confirmNextRound = false;
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			lp.add(p.toPlayerEntity());
			if (p.getName().contentEquals(username)) {
				canDraw = p.canDraw();
				myDrawCost = drawCost(p);
				phase = p.getPhase();
				confirmNextRound = p.isConfirmNextRound();
				for (j=0;j<p.getHand().size();j++) {
					CardEntity e = p.getHand().get(j).toCardEntity();
					e.setCanDiscard(p.canDiscard(j));
					lc.add(e);
				}
				
				for (j=0;j<discard.size();j++) {
					CardEntity e = discard.get(j).toCardEntity();
					e.setCanTake(p.canTake(j));
					ls.add(e);
				}
			}
		}
		entity.setCanDraw(canDraw);
		entity.setPlayers(lp);
		entity.setMyHand(lc);
		entity.setDiscard(ls);
		entity.setPhase(phase);
		entity.setMyDrawCost(myDrawCost);
		entity.setConfirmed(confirmNextRound);
		
		// deal with end round msg
		if (board.getStatus() != Consts.ROUNDEND) {
			entity.setEndRoundInfo(new ArrayList<>());
		} else {
			List<EndRoundEntity> eres = new ArrayList<>();
			for (i=0;i<=5;i++) {
				int stockNum = i+5;
				EndRoundEntity ere = new EndRoundEntity();
				
				int sh = shareholder.get(stockNum);
				if (sh < 0) {
					ere.setShareholder("没有");
				} else {
					ere.setShareholder(players.get(sh).getName());
				}
				
				Card tc = new Card(stockNum);
				ere.setStockName(tc.getName());
				ere.setNum(tc.getNum());
				ere.setIcon(Integer.toString(tc.getNum()));
				ere.setCard(tc.toCardEntity());
				
				List<Integer> coin1Before = new ArrayList<>();
				List<Integer> coin1After = new ArrayList<>();
				List<Integer> coin3Before = new ArrayList<>();
				List<Integer> coin3After = new ArrayList<>();
				List<String> names = new ArrayList<>();
				List<Integer> numStocks = new ArrayList<>();
				List<Integer> totalCoins = new ArrayList<>();
				List<String> sdd = new ArrayList<>();
				List<Integer> scores = new ArrayList<>();
				for (j=0;j<players.size();j++) {
					Player p = players.get(j);
					names.add(p.getNameDisplay());
					coin1Before.add(p.getCoin1s().get(i));
					coin3Before.add(p.getCoin3s().get(i));
					coin1After.add(p.getCoin1s().get(i+1));
					coin3After.add(p.getCoin3s().get(i+1));
					numStocks.add(p.numStock(stockNum));
					totalCoins.add(p.getCoin1s().get(i+1) + p.getCoin3s().get(i+1) * 3);
					
					if (p.getLastScore() < 0) {
						sdd.add(Integer.toString(p.getLastScore()));
					} else {
						sdd.add("+" + Integer.toString(p.getLastScore()));
					}
					scores.add(p.getTotalScore());
				}
				ere.setCoin1Before(coin1Before);
				ere.setCoin1After(coin1After);
				ere.setCoin3Before(coin3Before);
				ere.setCoin3After(coin3After);
				ere.setPlayerNames(names);
				ere.setNumStocks(numStocks);
				ere.setTotalCoins(totalCoins);
				ere.setScoreDeltaDisplay(sdd);
				ere.setScores(scores);
				eres.add(ere);
			}
			entity.setEndRoundInfo(eres);
		}
		
		//deal with end game msg
		EndGameEntity endGameInfo = new EndGameEntity();
		if (board.getStatus() == Consts.ENDGAME) {
			List<Player> tps = new ArrayList<>();
			for (i=1;i<=players.size();i++) {
				for (j=0;j<players.size();j++) {
					if (players.get(j).getRanking() == i) {
						tps.add(players.get(j));
					}
				}
			}
			
			List<String> egpn = new ArrayList<>();
			List<List<Integer>> egs = new ArrayList<>();
			List<Integer> egfs = new ArrayList<>();
			
			for (i=0;i<tps.size();i++) {
				if (i==0) {
					endGameInfo.setWinner(tps.get(0).toPlayerEntity().getAccount());
				}
				egpn.add(tps.get(i).getNameDisplay());
				egs.add(tps.get(i).getScores());
				egfs.add(tps.get(i).getTotalScore());
				if (tps.get(i).getName().contentEquals(username)) {
					endGameInfo.setEndGameRewards(tps.get(i).getEndGameRewards());
				}
			}
			endGameInfo.setPlayerNames(egpn);
			endGameInfo.setScores(egs);
			endGameInfo.setFinalScore(egfs);
			
		}
		entity.setEndGameInfo(endGameInfo);
		
		
		return entity;
	}
	
	public Startups(Board board) {
		this.board = board;
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		logger = new Logger();
		round = 0;
		
		antiMonopoly = new HashMap<>();
		shareholder = new HashMap<>();
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startRound() {
		// Step 1: add all cards
		List<Card> allCards = new ArrayList<>();
		int i,j;
		for (i=5;i<=10;i++) {
			for (j=0;j<i;j++) {
				allCards.add(new Card(i));
			}
		}
		
		// Step 2: set antiMonopoly
		antiMonopoly = new HashMap<>();
		for (i=5;i<=10;i++) {
			antiMonopoly.put(i, -1);
		}
		
		// Step 3: shuffle
		deck = new ArrayList<>();
		Random rand = new Random();
		while (allCards.size()>0) {
			int x = rand.nextInt(allCards.size());
			deck.add(allCards.remove(x));
		}
		
		// Step 4: remove first 5 cards
		for (i=0;i<Consts.REMOVENUM;i++) {
			removeTopCard();
		}
		
		// Step 5: deal cards and coins
		for (i=0;i<players.size();i++) {
			players.get(i).startRound();
		}
		
		// Step 6: empty discards
		discard = new ArrayList<>();
		
		// Step 7: set status, round & curPlayer
		board.setStatus(Consts.INGAME);
		round++;
		logger.logRoundStart(round);
		curPlayer = firstPlayer;
		players.get(curPlayer).startTurn();
		logger.logStartTurn(players.get(curPlayer));
		
	}
	
	public Card removeTopCard() {
		if (deck.size() == 0) {
			return null;
		} else {
			return deck.remove(0);
		}
	}
	
	public int drawCost(Player p) {
		int ans = 0;
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				ans++;
			}
			
		}
		return ans;
	}
	
	public void placeCoins(Player p) {
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				discard.get(i).receiveCoin();
			}
		}
	}
	
	public boolean potentialChangeAntiMonopoly(Player p, Card c) {
		int num = c.getNum();
		int ai = antiMonopoly.get(num);
		boolean f = false;
		if (ai == -1) {
			f = true;
		} else {
			Player p2 = players.get(ai);
			if (p2.numStockPlayed(num) < p.numStockPlayed(num)) {
				f = true;
			}
		}
		if (f) {
			int o = antiMonopoly.get(num);
			antiMonopoly.put(num, p.getIndex());
			
			if (o == -1) {
				logger.logAntiMonopoly(p, c, null);
			} else {
				logger.logAntiMonopoly(p, c, players.get(o));
			}
		}
		return f;
	}
	
	public void nextPlayer() {
		if (board.getStatus() == Consts.INGAME) {
			curPlayer++;
			if (curPlayer == players.size()) {
				curPlayer = 0;
			}
			players.get(curPlayer).startTurn();
			logger.logStartTurn(players.get(curPlayer));
		}
	}
	
	public boolean gameEnd() {
		boolean f = false;
		if (round == 4) f = true;
		return f;
	}
	
	public void endGame() {
		// Step 1: change status
		board.setStatus(Consts.ENDGAME);
		
		// Step 2: set rankings for players;
		int i,j;
		List<Player> tps = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			tps.add(players.get(i));
		}
		for (i=0;i<tps.size();i++) {
			for (j=i+1;j<tps.size();j++) {
				boolean shouldSwap = false;
				if (tps.get(i).getTotalScore() < tps.get(j).getTotalScore()) { // one with more total scores goes first
					shouldSwap = true;
				} else if (tps.get(i).getTotalScore() == tps.get(j).getTotalScore()) {
					if (tps.get(i).numScores(2) < tps.get(i).numScores(2)) { // one with more 2s goes first
						shouldSwap = true;
					} else if (tps.get(i).numScores(2) == tps.get(i).numScores(2)) {
						if (tps.get(i).numScores(1) < tps.get(i).numScores(1)) { // one with more 1s goes fitrt
							shouldSwap = true;
						} else if (tps.get(i).numScores(1) == tps.get(i).numScores(1)) {
							if (tps.get(i).finalCoins() < tps.get(j).finalCoins()) { // one with larger final score ranks higher
								shouldSwap = true;
							} else if (tps.get(i).finalCoins() == tps.get(j).finalCoins()) {
								if (tps.get(i).getCoin3RoundEnd() < tps.get(j).getCoin3RoundEnd()) { // same score, one with more coin3 ranks higher
									shouldSwap = true;
								} else if (tps.get(i).getCoin3RoundEnd() == tps.get(j).getCoin3RoundEnd()) {
									// get real index
									int rsi = tps.get(i).getIndex();
									int rsj = tps.get(j).getIndex();
									if (rsi < firstPlayer) rsi=rsi+players.size();
									if (rsj < firstPlayer) rsj=rsj+players.size();
									if (rsi < rsj) { // one goes last ranks higher
										shouldSwap = true;
									}
								}
							}
							
						}
					}
				}
				
				if (shouldSwap) {
					Player tp = tps.get(i);
					tps.set(i, tps.get(j));
					tps.set(j, tp);
				}
			}
		}
		
		for (i=0;i<tps.size();i++) {
			tps.get(i).setRanking(i+1);
		}
		
		// Step 3: deal with awards
		for (i=0;i<players.size();i++) {
			Account a = new Account();
			Player p = players.get(i);
			a.getFromDB(p.getName());
			List<Transaction> rewards = a.endGameReward(p.getRanking());
			List<String> endGameRewards = new ArrayList<>();
			for (j=0;j<rewards.size();j++) {
				a.addNewTransaction(rewards.get(j));
				endGameRewards.add(rewards.get(j).toString());
			}
			p.setEndGameRewards(endGameRewards);
			a.updateAccountDB();
		}
		
	}
	
	public boolean roundEnd() {
		boolean f = false;
		if (deck.size() == 0) f = true;
		return f;
	}
	
	public void endRound() {
		// Step 1: change status?
		board.setStatus(Consts.ROUNDEND);
		
		// Step 2: get shareholder for each stock
		int i,j;
		shareholder = new HashMap<>();
		for (i=5;i<=10;i++) {
			int t = 0;
			
			for (j=0;j<players.size();j++) {
				if (players.get(j).numStock(i) > t) {
					t = players.get(j).numStock(i);
				}
			}
			
			int ip = -1;
			for (j=0;j<players.size();j++) {
				if (players.get(j).numStock(i) == t) {
					if (ip == -1) {
						ip = j;
					} else {
						ip = -2; // multiple shareholder = no shareholder
					}
				}
			}
			shareholder.put(i, ip);
		}
		
		// Step 3: change coins
		for (i=0;i<players.size();i++) {
			players.get(i).setCoin1RoundEnd(players.get(i).getCoins());
			players.get(i).setCoin3RoundEnd(0);
		}
		for (i=5;i<=10;i++) {
			int sh = shareholder.get(i);
			int y = 0;
			for (j=0;j<players.size();j++) {
				if (sh>=0 && j != sh) {
					int x = players.get(j).numStock(i);
					players.get(j).subtractCoin1RoundEnd(x);
					players.get(j).addCoin3RoundEnd(0);
					y = y+x;
				} else {
					players.get(j).subtractCoin1RoundEnd(0);
					if (j!=sh) players.get(j).addCoin3RoundEnd(0);
				}
			}
			if (sh>=0) players.get(sh).addCoin3RoundEnd(y);
		}
		
		// Step 4: get rankings
		List<Player> lp = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			lp.add(players.get(i));
		}
		for (i=0;i<lp.size();i++) {
			for (j=i+1;j<lp.size();j++) {
				boolean needSwap = false;
				if (lp.get(i).finalCoins() < lp.get(j).finalCoins()) { // one with larger final score ranks higher
					needSwap = true;
				} else if (lp.get(i).finalCoins() == lp.get(j).finalCoins()) {
					if (lp.get(i).getCoin3RoundEnd() < lp.get(j).getCoin3RoundEnd()) { // same score, one with more coin3 ranks higher
						needSwap = true;
					} else if (lp.get(i).getCoin3RoundEnd() == lp.get(j).getCoin3RoundEnd()) {
						// get real index
						int rsi = lp.get(i).getIndex();
						int rsj = lp.get(j).getIndex();
						if (rsi < firstPlayer) rsi=rsi+players.size();
						if (rsj < firstPlayer) rsj=rsj+players.size();
						if (rsi < rsj) { // one goes last ranks higher
							needSwap = true;
						}
					}
				}
				if (needSwap) {
					Player tp = lp.get(i);
					lp.set(i, lp.get(j));
					lp.set(j, tp);
				}
			}
		}
		int firstPlayerIndex = -1;
		int secondPlayerIndex = -1;
		int lastPlayerIndex = -1;
		
		firstPlayerIndex = lp.get(0).getIndex();
		if (lp.size() > 1) secondPlayerIndex = lp.get(1).getIndex();
		if (lp.size() > 2) lastPlayerIndex = lp.get(lp.size()-1).getIndex();
		
		// Step 5: update scores
		for (i=0;i<players.size();i++) {
			if (players.get(i).getIndex() == firstPlayerIndex) {
				players.get(i).addScore(Consts.FIRSTPLACESCORE);
			} else if (players.get(i).getIndex() == secondPlayerIndex) {
				players.get(i).addScore(Consts.SECONDPLACESCORE);
			} else if (players.get(i).getIndex() == lastPlayerIndex) {
				players.get(i).addScore(Consts.LASTPLACESCORE);
			}
		}
		
		// Step 6: set first player
		lastPlayerIndex = lp.get(lp.size()-1).getIndex();
		//System.out.println("Last Player Index:" + lastPlayerIndex);
		firstPlayer = lastPlayerIndex;
		
		// Step 7: make sure no player is confirmed
		for (i=0;i<players.size();i++) {
			players.get(i).setConfirmNextRound(false);
		}
		
		// Step 8: Log round end
		logger.logRoundEnd(round);
		
	}
	
	public boolean allConfirmedNextRound() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isConfirmNextRound() == false) {
				return false;
			}
		}
		return true;
	}
	
	// Start actual operations
	// Actual start game operation
	public void startGameUDB() {
		// Step 1: create players
		int i;
		List<String> playerNames = board.getPlayerNames();
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Player p = new Player(playerNames.get(i));
			p.setStartups(this);
			players.add(p);
		}
		
		// Step 2: set round and curPlayer
		round = 0;
		curPlayer = board.getFirstPlayer();
		firstPlayer = board.getFirstPlayer();
		startRound();
		
		updatePlayers();
		updateBasicDB();
	}
	

	// Actual draw operation
	public void playerDrawUDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			if (p.canDraw() && p.getIndex() == curPlayer && p.getPhase() == Consts.DRAWORTAKE) {
				p.draw();
				updateBasicDB();
				updatePlayer(name);
			}
		}
	}
	
	// Actual discard operation
	public void playerDiscardUDB(String name, int cardIndex) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.discard(cardIndex);
			updateBasicDB();
			updatePlayers();
		}
	}
	
	// Actual play operation
	public void playerPlayUDB(String name, int cardIndex) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.play(cardIndex);
			updateBasicDB();
			updatePlayers();
		}
	}
	
	// Actual take operation
	public void playerTakeUDB(String name, int cardIndex) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.take(cardIndex);
			updateBasicDB();
			updatePlayer(name);
		}
	}
	
	// Actual confirm next round operation
	public void playerConfirmNextRoundUDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.setConfirmNextRound(true);
			updatePlayer(name);
			if (allConfirmedNextRound()) {
				if (gameEnd()) {
					endGame();
				} else {
					startRound();
				}
				updateBasicDB();
				updatePlayers();
			}
		}
	}
	
	
	// End actual operations
	
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void updateBasicDB() {
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		List<Document> dos = new ArrayList<>();
		for (i=0;i<discard.size();i++) {
			dos.add(discard.get(i).toDocument());
		}
		updateDB("deck", dok);
		updateDB("discard", dos);
		
		updateDB("round", round);
		updateDB("status", board.getStatus());
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("antiMonopoly", hashMapToDocument(antiMonopoly));
		updateDB("shareholder", hashMapToDocument(shareholder));
		updateDB("logs", logger.getLogs());
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}
	
	public boolean hasPlayer(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return true;
			}
		}
		return false;
	}

	public String getId() {
		return board.getId();
	}
	public void setId(String id) {
		board.setId(id);
	}
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getFirstPlayer() {
		return board.getFirstPlayer();
	}
	public void setFirstPlayer(int firstPlayer) {
		board.setFirstPlayer(firstPlayer);
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public HashMap<Integer, Integer> getAntiMonopoly() {
		return antiMonopoly;
	}
	public void setAntiMonopoly(HashMap<Integer, Integer> antiMonopoly) {
		this.antiMonopoly = antiMonopoly;
	}
	public HashMap<Integer, Integer> getShareholder() {
		return shareholder;
	}
	public void setShareholder(HashMap<Integer, Integer> shareholder) {
		this.shareholder = shareholder;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
