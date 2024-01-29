package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.play.Game;
import com.cosine.cosgame.pokerworld.entity.BoardEntity;
import com.cosine.cosgame.pokerworld.entity.PlayerEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	String dominantRank;
	String dominantSuit;
	String currentSuit;
	String rawHidden;
	
	int numDominant;
	int curClaimedPlayer;
	int status;
	int firstPlayer;
	int curPlayer;
	int banker;
	int numPlayed;
	int winPlayer;
	int attackerPointsGained;
	int round;
	int phase;
	int biggestRank;
	int gameMode;
	int numWzRevealed;
	int numJeRevealed;
	List<Integer> settings;
	List<Player> players;
	List<List<Integer>> sequences;
	List<Boolean> confirmed;
	
	PokerCard dominantCard;
	PokerCard firstCard;
	
	GameUtil gameUtil;
	
	MongoDBUtil dbutil;
	
	public Board() {
		settings = new ArrayList<>();
		players = new ArrayList<>();
		sequences = new ArrayList<>();
		confirmed = new ArrayList<>();
		dominantCard = new PokerCard();
		firstCard = new PokerCard();
		
		lord = "";
		currentSuit = "";
		gameUtil = new GameUtil();
		gameUtil.setBoard(this);
		
		String dbname = "pokerworld";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		rawHidden = "";
		gameMode = -1;
	}
	
	public void startGame() {
		gameMode = settings.get(Consts.GAMEMODEINDEX);
		if (gameMode == Consts.SFSJ) {
			startGameSFSJ();
		} else if (gameMode == Consts.WIZARD) {
			startGameWizard();
		}
	}
	
	public void startGameSFSJ() {
		int i;
		confirmed = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			players.get(i).setInnerId(i);
			confirmed.add(false);
		}
		gameUtil.newGame();
		gameUtil.sortCards();
		sequences = gameUtil.getSequences();
		dominantRank = "2";
		dominantSuit = "x";
		numDominant = 0;
		curClaimedPlayer = -1;
		numPlayed = -1;
		rawHidden = gameUtil.toRawHidden();
		status = Consts.DISTRIBUTECARDS;
	}
	
	public void startGameWizard() {
		biggestRank = settings.get(Consts.BIGGESTRANKINDEX);
		firstPlayer = settings.get(Consts.FIRSTPLAYERINDEX);
		confirmed = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			players.get(i).clearAll();
		}
		round = 1;
		dealWizard();
		status = Consts.BIDTRICKS;
	}
	
	public void dealWizard() {
		int i,j;
		List<PokerCard> deck = PokerUtil.getWizardDeck();
		deck = PokerUtil.shuffle(deck);
		
		for (i=0;i<players.size();i++) {
			players.get(i).emptyHand();
			
			for (j=0;j<round;j++) {
				PokerCard c = deck.remove(0);
				players.get(i).getHand().add(c);
			}
			
		}
		
		dominantCard = new PokerCard();
		
		if (deck.size() > 0) {
			numWzRevealed = 0;
			numJeRevealed = 0;
			dominantSuit = "";
			while (deck.size()>0) {
				dominantCard = deck.remove(0);
				if (dominantCard.getSuit().contentEquals("WZ")) {
					numWzRevealed++;
				} else if (dominantCard.getSuit().contentEquals("JE")) {
					numJeRevealed++;
				} else {
					dominantSuit = dominantCard.getSuit();
					break;
				}
			}
			
		} else {
			dominantSuit = "";
		}
		
		for (i=0;i<players.size();i++) {
			players.get(i).sortHand();
		}
	}
	
	public boolean allBid() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getBidThisRound() == -1) {
				return false;
			}
		}
		return true;
	}
	
	public void startRoundWizard() {
		firstCard = new PokerCard();
	}
	
	public void drawHidden() {
		if (curClaimedPlayer != -1) {
			firstPlayer = curClaimedPlayer;
		}
		gameUtil.addHiddenCardsToHand(firstPlayer, rawHidden);
		status = Consts.DRAWHIDDEN;
	}
	
	public void hide(int index, List<Integer> playedIndex) {
		rawHidden = gameUtil.removeHiddenCardsFromHand(index, playedIndex);
		curPlayer = firstPlayer;
		status = Consts.PLAYCARDS;
	}
	
	public boolean isLord(String username) {
		if (username == null) return false;
		if (lord == null) return true;
		if (lord.contentEquals(username)) return true;
		return false;
	}
	
	public void claimDominant(String dominantSuit, int numDominant, int curClaimedPlayer) {
		if (this.numDominant < numDominant) {
			this.dominantSuit = dominantSuit;
			this.numDominant = numDominant;
			this.curClaimedPlayer = curClaimedPlayer;
		}
	}
	
	public void endDistribute(int index) {
		if (index >=0 && index<players.size()) {
			players.get(index).setConfirmedClaim(true);
		}
		boolean allConfirmed = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isConfirmedClaim() == false) {
				allConfirmed = false;
			}
		}
		if (allConfirmed) {
			this.firstPlayer = curClaimedPlayer;
			gameUtil.claimDominantSuit(dominantSuit, curClaimedPlayer);
			drawHidden();
		}
	}
	
	public void nextPlayerPlay() {
		curPlayer++;
		curPlayer = curPlayer % players.size();
		/*
		Player p = players.get(curPlayer);
		if (p.getPlayedIndex() != null && p.getPlayedIndex().size() != 0) {
			endRound();
		}*/
		if (curPlayer == firstPlayer) {
			endRound();
		}
	}
	
	public void endRound() {
		List<Integer> roundResult = gameUtil.judgeRound(this);
		winPlayer = roundResult.get(0);
		firstPlayer = winPlayer;
		curPlayer = -1;
		attackerPointsGained = gameUtil.getAttackerPointsGained();
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setConfirmedNextTurn(false);
		}
		
	}
	
	public void confirmNextTurn(String username) {
		Player p = getPlayerByName(username);
		if (p != null) {
			p.setConfirmedNextTurn(true);
		}
		int i;
		boolean flag = true;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isConfirmedNextTurn() == false) {
				flag = false;
				break;
			}
		}
		if (flag) {
			newRound();
		}
	}
	
	public void newRound() {
		curPlayer = firstPlayer;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).emptyPlayedIndex();
		}
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public List<Integer> getSettings() {
		return settings;
	}
	public void setSettings(List<Integer> settings) {
		this.settings = settings;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public GameUtil getGameUtil() {
		return gameUtil;
	}
	public void setGameUtil(GameUtil gameUtil) {
		this.gameUtil = gameUtil;
	}
	public Game getGame() {
		return gameUtil.getGame();
	}
	public String getDominantRank() {
		return dominantRank;
	}
	public void setDominantRank(String dominantRank) {
		this.dominantRank = dominantRank;
	}
	public String getDominantSuit() {
		return dominantSuit;
	}
	public void setDominantSuit(String dominantSuit) {
		this.dominantSuit = dominantSuit;
	}
	public List<List<Integer>> getSequences() {
		return sequences;
	}
	public void setSequences(List<List<Integer>> sequences) {
		this.sequences = sequences;
	}
	public int getNumDominant() {
		return numDominant;
	}
	public void setNumDominant(int numDominant) {
		this.numDominant = numDominant;
	}
	public int getCurClaimedPlayer() {
		return curClaimedPlayer;
	}
	public void setCurClaimedPlayer(int curClaimedPlayer) {
		this.curClaimedPlayer = curClaimedPlayer;
	}
	public int getBanker() {
		return banker;
	}
	public void setBanker(int banker) {
		this.banker = banker;
	}
	public String getRawHidden() {
		return rawHidden;
	}
	public void setRawHidden(String rawHidden) {
		this.rawHidden = rawHidden;
	}
	public int getNumPlayed() {
		return numPlayed;
	}
	public void setNumPlayed(int numPlayed) {
		this.numPlayed = numPlayed;
	}
	public int getWinPlayer() {
		return winPlayer;
	}
	public void setWinPlayer(int winPlayer) {
		this.winPlayer = winPlayer;
	}
	public List<Boolean> getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(List<Boolean> confirmed) {
		this.confirmed = confirmed;
	}
	public int getAttackerPointsGained() {
		return attackerPointsGained;
	}
	public void setAttackerPointsGained(int attackerPointsGained) {
		this.attackerPointsGained = attackerPointsGained;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getBiggestRank() {
		return biggestRank;
	}
	public void setBiggestRank(int biggestRank) {
		this.biggestRank = biggestRank;
	}
	public int getGameMode() {
		return gameMode;
	}
	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}
	public String getCurrentSuit() {
		return currentSuit;
	}
	public void setCurrentSuit(String currentSuit) {
		this.currentSuit = currentSuit;
	}
	public PokerCard getDominantCard() {
		return dominantCard;
	}
	public void setDominantCard(PokerCard dominantCard) {
		this.dominantCard = dominantCard;
	}

	public void addPlayer(String name) {
		Player p = new Player(this);
		p.setName(name);
		p.setIndex(players.size());
		players.add(p);
	}
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
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
	public void updateBasicDB() {
		//TODO: Add more items for general updates
		dbutil.update("id", id, "status", status);
		dbutil.update("id", id, "round", round);
		dbutil.update("id", id, "phase", phase);
		dbutil.update("id", id, "curPlayer", curPlayer);
		dbutil.update("id", id, "numPlayed", numPlayed);
		dbutil.update("id", id, "winPlayer", winPlayer);
		dbutil.update("id", id, "firstPlayer", firstPlayer);
		dbutil.update("id", id, "confirmed", confirmed);
		dbutil.update("id", id, "attackerPointsGained", attackerPointsGained);
		//dbutil.update("id", id, "cards", gameUtil.toRawCards());
	}
	public void updateCardsDB() {
		dbutil.update("id", id, "cards", gameUtil.toRawCards());
	}
	public void updateDominantDB() {
		//TODO: Add more items for general updates
		dbutil.update("id", id, "dominantRank", dominantRank);
		dbutil.update("id", id, "dominantSuit", dominantSuit);
		dbutil.update("id", id, "numDominant", numDominant);
		dbutil.update("id", id, "curClaimedPlayer", curClaimedPlayer);
		dbutil.update("id", id, "dominantCard", dominantCard.toString());
		dbutil.update("id", id, "numWzRevealed", numWzRevealed);
		dbutil.update("id", id, "numJeRevealed", numJeRevealed);
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
	public Player getPlayerByIndex(int index) {
		if (index<0 || index>=players.size()) {
			return null;
		}
		return players.get(index);
	}
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
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
	public void removePlayerFromDB(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				removePlayerFromDB(i);
				break;
			}
		}
	}
	public void dismiss() {
		dbutil.delete("id", id);
	}
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("phase", phase);
		doc.append("firstPlayer", firstPlayer);
		doc.append("curPlayer", curPlayer);
		doc.append("settings", settings);
		doc.append("cards", gameUtil.toRawCards());
		doc.append("sequences", sequences);
		doc.append("dominantRank", dominantRank);
		doc.append("dominantSuit", dominantSuit);
		doc.append("currentSuit", currentSuit);
		doc.append("numDominant", numDominant);
		doc.append("curClaimedPlayer", curClaimedPlayer);
		doc.append("rawHidden", rawHidden);
		doc.append("numPlayed", numPlayed);
		doc.append("winPlayer", winPlayer);
		doc.append("confirmed", confirmed);
		doc.append("attackerPointsGained", attackerPointsGained);
		doc.append("biggestRank", biggestRank);
		doc.append("gameMode", gameMode);
		doc.append("dominantCard", dominantCard.toString());
		doc.append("numWzRevealed", numWzRevealed);
		doc.append("numJeRevealed", numJeRevealed);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String n = players.get(i).getName();
			playerNames.add(n);
			n = "player-" + n;
			doc.append(n, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		round = doc.getInteger("round", -1);
		phase = doc.getInteger("phase", -1);
		firstPlayer = doc.getInteger("firstPlayer", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		settings = (List<Integer>) doc.get("settings");
		sequences = (List<List<Integer>>) doc.get("sequences");
		dominantRank = doc.getString("dominantRank");
		dominantSuit = doc.getString("dominantSuit");
		currentSuit = doc.getString("currentSuit");
		numDominant = doc.getInteger("numDominant", 0);
		curClaimedPlayer = doc.getInteger("curClaimedPlayer", -1);
		confirmed = (List<Boolean>) doc.get("confirmed");
		List<String> rawCards = (List<String>) doc.get("cards");
		gameUtil.buildCards(rawCards);
		rawHidden = doc.getString("rawHidden");
		numPlayed = doc.getInteger("numPlayed", -1);
		winPlayer = doc.getInteger("winPlayer", -1);
		attackerPointsGained = doc.getInteger("attackerPointsGained", 0);
		biggestRank = doc.getInteger("biggestRank", 13);
		gameMode = doc.getInteger("gameMode", 0);
		gameUtil.setAttackerPointsGained(attackerPointsGained);
		String dominantCardStr = doc.getString("dominantCard");
		dominantCard = new PokerCard(dominantCardStr);
		numWzRevealed = doc.getInteger("numWzRevealed", 0);
		numJeRevealed = doc.getInteger("numJeRevealed", 0);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			Player p = new Player(this);
			p.setIndex(i);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(status);
		entity.setRound(round);
		entity.setPhase(phase);
		entity.setDominantRank(dominantRank);
		entity.setDominantSuit(dominantSuit);
		entity.setNumDominant(numDominant);
		entity.setCurClaimedPlayer(curClaimedPlayer);
		entity.setCurPlayer(curPlayer);
		entity.setFirstPlayer(firstPlayer);
		entity.setNumPlay(numPlayed);
		entity.setWinPlayer(winPlayer);
		entity.setAttackerPointsGained(attackerPointsGained);
		entity.setGameMode(gameMode);
		entity.setBiggestRank(biggestRank);
		entity.setDominantCard(dominantCard.toString());
		entity.setNumWzRevealed(numWzRevealed);
		entity.setNumJeRevealed(numJeRevealed);
		int i;
		List<PlayerEntity> playerEntities = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerEntities.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				Player p = players.get(i);
				entity.setMyIndex(p.getInnerId());
				if (gameMode == Consts.SFSJ) {
					entity.setMyCards(p.getMyRawCardsAfterPlay());
				} else {
					entity.setMyCards(p.getHandAsStr());
				}
				
				entity.setConfirmed(p.isConfirmedClaim());
				entity.setConfirmedNextTurn(p.isConfirmedNextTurn());
				if (sequences == null || sequences.size()<=i) {
					entity.setSequence(new ArrayList<>());
				} else {
					entity.setSequence(sequences.get(i));
				}
				
			}
		}
		entity.setPlayers(playerEntities);
		return entity;
	}
	
}
