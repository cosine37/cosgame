package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<List<Card>> playedCards;
	List<Card> ancient;
	List<Card> deck;
	List<String> avatars;
	List<Pm> pmToChoose;
	int status;
	int round;
	int turn;
	int curPlayer;
	String id;
	String lord;
	AllRes allRes;
	Logger logger;
	MongoDBUtil dbutil;
	
	public Board(){
		players = new ArrayList<>();
		playedCards = new ArrayList<>();
		ancient = new ArrayList<>();
		deck = new ArrayList<>();
		avatars = new ArrayList<>();
		pmToChoose = new ArrayList<>();
		logger = new Logger();
		allRes = new AllRes();
		
		String dbname = "pokewhat";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void newBoard() {
		avatars = allRes.getAvatars();
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setAvatar(avatars.get(0));
		}
	}
	
	public void genDeck() {
		int i,j;
		final int NUMSPELL = 8;
		for (i=1;i<=NUMSPELL;i++) {
			for (j=1;j<=i;j++) {
				Card c = CardFactory.createCard(i);
				deck.add(c);
			}
		}
	}
	
	public void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void removeCards(int x) {
		int numRemove = 0;
		int i,j;
		if (x == 2) {
			numRemove = 12;
		} else if (x == 3) {
			numRemove = 6;
		}
		for (i=0;i<numRemove;i++) {
			Card c = deck.remove(0);
			int index = c.getNum();
			playedCards.get(index).add(c);
		}
		
	}
	
	public void genAncient() {
		for (int i=0;i<PokewhatConsts.NUMANCIENT;i++) {
			Card c = deck.remove(0);
			ancient.add(c);
		}
	}
	
	public void deal() {
		int i,j;
		for (i=0;i<players.size();i++) {
			for (j=0;j<PokewhatConsts.HANDSIZE;j++) {
				Card c = deck.remove(0);
				players.get(i).getHand().add(c);
			}
			players.get(i).setHp(PokewhatConsts.MAXHP);
		}
	}
	
	public void startGame() {
		int x = players.size()+PokewhatConsts.NUMPMTOCHOOSE-1;
		pmToChoose = allRes.getPmToChoose(x);
		status = PokewhatConsts.CHOOSEPM;
		players.get(curPlayer).setPhase(PokewhatConsts.USEMOVE);
	}
	
	public void choosePm(int playerIndex, int pmIndex) {
		Player p = players.get(playerIndex);
		Pm pm = pmToChoose.remove(pmIndex);
		p.setPm(pm);
		logger.logSend(p);
		p.setPhase(PokewhatConsts.OFFTURN);
		curPlayer++;
		if (curPlayer>=players.size()) {
			curPlayer = 0;
		}
		Player cp = players.get(curPlayer);
		cp.setPhase(PokewhatConsts.USEMOVE);
		if (cp.getPm().getImg() != null && cp.getPm().getImg() != "") {
			status = PokewhatConsts.INGAME;
			nextRound();
		}
	}
	
	public void botChoosePm() {
		Player p = players.get(curPlayer);
		if (p.isBot()) {
			Random rand = new Random();
			int x = rand.nextInt(PokewhatConsts.NUMPMTOCHOOSE);
			choosePm(curPlayer, x);
		}
	}
	
	public boolean isRoundEnd() {
		boolean ans = false;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getHp() == 0) {
				ans = true;
			}
		}
		return ans;
	}
	
	public void endRound() {
		int i;
		logger.logEndRound(round);
		for (i=0;i<players.size();i++) {
			if (i == curPlayer) {
				continue;
			}
			if (players.get(i).getHp() > 0) {
				int x = 1+players.get(i).getAncient().size();
				players.get(i).setScoreLastRound(x);
				players.get(i).addScore(x);
				logger.logScore(players.get(i), x);
			} else {
				players.get(i).setScoreLastRound(0);
				logger.logScore(players.get(i), 0);
			}
		}
		if (players.get(curPlayer).getHp() > 0) {
			int x = 3+players.get(curPlayer).getAncient().size();
			players.get(curPlayer).setScoreLastRound(x);
			players.get(curPlayer).addScore(x);
			logger.logScore(players.get(curPlayer), x);
		} else {
			players.get(curPlayer).setScoreLastRound(0);
			logger.logScore(players.get(curPlayer), 0);
		}
		for (i=0;i<players.size();i++) {
			if (players.get(i).getScore() >= PokewhatConsts.GAMEENDSCORE) {
				logger.logGameEnd();
				endGame();
				return;
			}
		}
		curPlayer++;
		if (curPlayer>=players.size()) {
			curPlayer = 0;
		}
		nextRound();
	}
	
	public void nextRound() {
		int i;
		
		round++;
		turn = 1;
		playedCards = new ArrayList<>();
		for (i=0;i<9;i++) {
			List<Card> ls = new ArrayList<>();
			playedCards.add(ls);
		}
		for (i=0;i<players.size();i++) {
			List<Card> lh = new ArrayList<>();
			List<Card> la = new ArrayList<>();
			players.get(i).setHand(lh);
			players.get(i).setAncient(la);
			players.get(i).setLastMove(0);
			players.get(i).setMissCount(0);
			players.get(i).setPhase(PokewhatConsts.OFFTURN);
		}
		deck = new ArrayList<>();
		ancient = new ArrayList<>();
		genDeck();
		shuffle();
		removeCards(players.size());
		genAncient();
		deal();
		players.get(curPlayer).setPhase(PokewhatConsts.USEMOVE);
		logger.logStartRound(round);
		logger.logStartTurn(players.get(curPlayer));
	}
	
	public void endGame() {
		status = PokewhatConsts.ENDGAME;
	}
	
	public void eternabeam(int index) {
		for (int i=0;i<players.size();i++) {
			if (i==index) {
				logger.logEternabeam(players.get(i));
				continue;
			} else {
				players.get(i).setHp(0);
			}
		}
		
	}
	
	public void addToPlayedCards(Card c) {
		int index = c.getNum();
		playedCards.get(index).add(c);
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public void addPlayer(String s) {
		Player p = new Player();
		p.setName(s);
		players.add(p);
	}
	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player();
		bot.setName(botName);
		bot.setBot(true);
		Random rand = new Random();
		int x = rand.nextInt(avatars.size());
		bot.setAvatar(avatars.get(x));
		players.add(bot);
		addPlayerToDB(botName);
	}
	public Player getPlayerByName(String name) {
		Player p = null;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				p = players.get(i);
				break;
			}
		}
		return p;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Card>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<Card>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public void nextTurn() {
		turn++;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public List<String> getAvatars() {
		return avatars;
	}
	public void setAvatars(List<String> avatars) {
		this.avatars = avatars;
	}
	public List<Pm> getPmToChoose() {
		return pmToChoose;
	}
	public void setPmToChoose(List<Pm> pmToChoose) {
		this.pmToChoose = pmToChoose;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		
		List<List<String>> allCards = new ArrayList<>();
		List<List<String>> playedCards = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		List<String> pms = new ArrayList<>();
		List<String> lptc = new ArrayList<>();
		List<String> lptcn = new ArrayList<>();
		List<String> hp = new ArrayList<>();
		List<String> scores = new ArrayList<>();
		List<String> la = new ArrayList<>();
		List<String> lsl = new ArrayList<>();
		List<String> lpa = new ArrayList<>();
		String phase = "0";
		String lastMove = "0";
		int i,j;
		for (i=0;i<this.playedCards.size();i++) {
			List<String> sl = new ArrayList<>();
			for (j=0;j<this.playedCards.get(i).size();j++) {
				sl.add(this.playedCards.get(i).get(j).getImg());
			}
			playedCards.add(sl);
		}
		int tn = PokewhatConsts.NUMPMTOCHOOSE;
		if (pmToChoose.size()<tn) {
			tn = pmToChoose.size();
		}
		for (i=0;i<tn;i++) {
			lptc.add(pmToChoose.get(i).getImg());
			lptcn.add(pmToChoose.get(i).getName());
		}
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			pms.add(players.get(i).getPm().getImg());
			hp.add(Integer.toString(players.get(i).getHp()));
			scores.add(Integer.toString(players.get(i).getScore()));
			lsl.add(Integer.toString(players.get(i).getScoreLastRound()));
			lpa.add(players.get(i).getAvatar());
			if (players.get(i).getName().contentEquals(name)) {
				phase = Integer.toString(players.get(i).getPhase());
				lastMove = Integer.toString(players.get(i).getLastMove());
				for (j=0;j<players.get(i).getAncient().size();j++) {
					la.add(players.get(i).getAncient().get(j).getImg());
				}
			}
			
			List<String> sl = new ArrayList<>();
			for (j=0;j<players.get(i).getHand().size();j++) {
				if (players.get(i).getName().contentEquals(name)) {
					sl.add("qs");
				} else {
					sl.add(players.get(i).getHand().get(j).getImg());
				}
			}
			allCards.add(sl);
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setCurPlayer(Integer.toString(curPlayer));
		entity.setRound(Integer.toString(round));
		entity.setTurn(Integer.toString(turn));
		entity.setStatus(Integer.toString(status));
		entity.setPhase(phase);
		entity.setLastMove(lastMove);
		entity.setPlayedCards(playedCards);
		entity.setPlayerNames(playerNames);
		entity.setScores(scores);
		entity.setAllCards(allCards);
		entity.setHp(hp);
		entity.setPm(pms);
		entity.setDeckSize(Integer.toString(deck.size()));
		entity.setAncientSize(Integer.toString(ancient.size()));
		entity.setAncient(la);
		entity.setScoreLastRound(lsl);
		entity.setAvatars(avatars);
		entity.setPlayerAvatars(lpa);
		entity.setPmToChoose(lptc);
		entity.setPmToChooseNames(lptcn);
		entity.setLogs(logger.getLogs());
		return entity;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("id",id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("turn", turn);
		doc.append("curPlayer", curPlayer);
		doc.append("logs", logger.getLogs());
		int i,j;
		List<String> lov = new ArrayList<>();
		for (i=0;i<avatars.size();i++) {
			lov.add(avatars.get(i));
		}
		doc.append("avatars", lov);
		List<Document> lopm = new ArrayList<>();
		for (i=0;i<pmToChoose.size();i++) {
			lopm.add(pmToChoose.get(i).toDocument());
		}
		doc.append("pmToChoose", lopm);
		List<String> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getImg());
		}
		doc.append("deck", lod);
		List<List<String>> lopc = new ArrayList<>();
		for (i=0;i<playedCards.size();i++) {
			List<Card> lc = playedCards.get(i);
			List<String> ls = new ArrayList<>();
			for (j=0;j<lc.size();j++) {
				ls.add(lc.get(j).getImg());
			}
			lopc.add(ls);
		}
		doc.append("playedCards", lopc);
		List<String> loa = new ArrayList<>();
		for (i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		doc.append("ancient", loa);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(playerName);
			String s = "player-" + playerName;
			doc.append(s, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		turn = doc.getInteger("turn", 0);
		curPlayer = doc.getInteger("curPlayer", 0);
		logger.setLogs((List<String>) doc.get("logs"));
		int i,j;
		List<String> lov = (List<String>) doc.get("avatars");
		avatars = new ArrayList<>();
		for (i=0;i<lov.size();i++) {
			avatars.add(lov.get(i));
		}
		List<Document> lopm = (List<Document>) doc.get("pmToChoose");
		pmToChoose = new ArrayList<>();
		for (i=0;i<lopm.size();i++) {
			Pm pm = new Pm();
			pm.setFromDoc(lopm.get(i));
			pmToChoose.add(pm);
		}
		List<String> lod = (List<String>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<lod.size();i++) {
			Card c = CardFactory.createCard(lod.get(i));
			c.setBoard(this);
			deck.add(c);
		}
		List<List<String>> lopc = (List<List<String>>) doc.get("playedCards");
		playedCards = new ArrayList<>();
		for (i=0;i<lopc.size();i++) {
			List<String> ls = lopc.get(i);
			List<Card> lc = new ArrayList<>();
			for (j=0;j<ls.size();j++) {
				Card c = CardFactory.createCard(ls.get(j));
				lc.add(c);
			}
			playedCards.add(lc);
		}
		List<String> loa = (List<String>) doc.get("ancient");
		ancient = new ArrayList<>();
		for (i=0;i<loa.size();i++) {
			Card c = CardFactory.createCard(loa.get(i));
			c.setBoard(this);
			ancient.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
	}
	
	public void storeToDB() {
		Document doc = toDocument();
		dbutil.insert(doc);
	}
	
	public void getFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void updatePmToChoose() {
		List<Document> lopm = new ArrayList<>();
		for (int i=0;i<pmToChoose.size();i++) {
			lopm.add(pmToChoose.get(i).toDocument());
		}
		dbutil.update("id", id, "pmToChoose", lopm);
	}
	
	public void updateLogs() {
		dbutil.update("id", id, "logs", logger.getLogs());
	}
	
	public void updateDeck() {
		List<String> lod = new ArrayList<>();
		for (int i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getImg());
		}
		dbutil.update("id", id, "deck", lod);
	}
	
	public void updatePlayedCards() {
		int i,j;
		List<List<String>> lopc = new ArrayList<>();
		for (i=0;i<playedCards.size();i++) {
			List<Card> lc = playedCards.get(i);
			List<String> ls = new ArrayList<>();
			for (j=0;j<lc.size();j++) {
				ls.add(lc.get(j).getImg());
			}
			lopc.add(ls);
		}
		dbutil.update("id", id, "playedCards", lopc);
	}
	
	public void updateAncient() {
		List<String> loa = new ArrayList<>();
		for (int i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		dbutil.update("id", id, "ancient", loa);
	}
	
	public void removePlayerFromDB(int index) {
		String playerName = "player-" + players.get(index).getName();
		players.remove(index);
		dbutil.removeKey("id", id, playerName);
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerName = players.get(i).getName();
			playerNames.add(players.get(i).getName());
		}
		dbutil.update("id", id, "playerNames", playerNames);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void dismiss() {
		dbutil.delete("id", id);
	}
}
