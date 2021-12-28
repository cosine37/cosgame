package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.base.LiuHong;
import com.cosine.cosgame.threechaodoms.entity.BoardEntity;
import com.cosine.cosgame.threechaodoms.entity.CardEntity;
import com.cosine.cosgame.threechaodoms.entity.PlayerEntity;
import com.cosine.cosgame.threechaodoms.logs.Logger;
import com.cosine.cosgame.threechaodoms.shop.Shop;
import com.cosine.cosgame.threechaodoms.shop.Transaction;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int status;
	int firstPlayer;
	int curPlayer;
	int weiPos;
	int hanPos;
	List<Player> players;
	List<Card> deck;
	List<Card> tavern;
	List<Card> exile;
	List<Card> tomb;
	
	Logger logger;
	
	MongoDBUtil dbutil;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		tavern = new ArrayList<>();
		exile = new ArrayList<>();
		tomb = new ArrayList<>();
		
		logger = new Logger();
		
		String dbname = "threechaodoms";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		AllRes allRes = new AllRes();
		deck = allRes.getDeck();
		status = Consts.SETUP;
		weiPos = Consts.STARTPOS;
		hanPos = Consts.STARTPOS;
		int i;
		List<ID> ids = shuffleIDs();
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			p.setPhase(Consts.OFFTURN);
			p.draw(5);
			ID e = ids.get(i);
			p.setId(e);
		}
		tavern = new ArrayList<>();
		for (i=0;i<Consts.TAVERNSIZE;i++) {
			Card c = deck.remove(0);
			tavern.add(c);
		}
		tomb = new ArrayList<>();
		Card liuHong = new LiuHong();
		tomb.add(liuHong);
		Random rand = new Random();
		firstPlayer = rand.nextInt(players.size());
		curPlayer = firstPlayer;
		players.get(firstPlayer).setPhase(Consts.MAKEHAND);
		logger.log("游戏开始");
	}
	
	public List<ID> shuffleIDs() {
		int i,j;
		List<ID> tempIds = new ArrayList<>();
		for (i=0;i<4;i++) {
			for (j=i+1;j<4;j++) {
				ID e = new ID();
				e.setFactions(i, j);
				tempIds.add(e);
			}
		}
		List<ID> ids = new ArrayList<>();
		while (tempIds.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(tempIds.size());
			ID e = tempIds.remove(x);
			ids.add(e);
		}
		return ids;
	}
	
	public Card takeFromTavern(int x) {
		if (x>=0 && x<tavern.size()) {
			Card c = tavern.get(x);
			if (c.isBlankSpace()) {
				return null;
			} else {
				tavern.set(x, new BlankSpaceCard());
				return c;
			}
		} else {
			return null;
		}
	}
	
	public Card takeFromTomb(int x) {
		if (x>=0 && x<tomb.size()) {
			Card c = tomb.remove(x);
			return c;
		} else {
			return null;
		}
	}
	
	public Card popTopDeck() {
		if (deck.size() == 0) {
			reshuffle();
		} 
		if (deck.size() == 0) {
			return null;
		}
		else {
			Card c = deck.remove(0);
			return c;
		}
	}
	
	public void reshuffle() {
		while (exile.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(exile.size());
			Card c = exile.remove(x);
			deck.add(c);
		}
	}
	
	public void refillTavern() {
		int i;
		for (i=0;i<tavern.size();i++) {
			if (tavern.get(i).isBlankSpace()) {
				Card c = popTopDeck();
				if (c == null) {
					return;
				} else {
					tavern.set(i, c);
				}
			}
		}
	}
	
	public int winFaction() {
		int ans = -1;
		if (status == Consts.ENDGAME) {
			if (weiPos >= Consts.CHAOSPOS && hanPos >= Consts.CHAOSPOS) {
				ans = Consts.QUN;
			} else if (weiPos - hanPos >=2) {
				ans = Consts.WEI;
			} else if (hanPos - weiPos >=2) {
				ans = Consts.HAN;
			} else {
				ans = Consts.WU;
			}
		}
		return ans;
	}
	
	public int winnerId() {
		int ans = -1;
		if (status == Consts.ENDGAME) {
			int winFaction = winFaction();
			int i;
			int maxNumFaction = -1;
			int minNumCard = 999;
			int maxDifficulty = -1;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getId().hasFaction(winFaction)) {
					if (players.get(i).numFaction(winFaction)>maxNumFaction) {
						maxNumFaction = players.get(i).numFaction(winFaction);
						minNumCard = players.get(i).totalCards();
						maxDifficulty = players.get(i).getId().getDifficulty();
						ans = i;
					} else if (players.get(i).numFaction(winFaction) == maxNumFaction) {
						if (players.get(i).totalCards()<minNumCard) {
							minNumCard = players.get(i).totalCards();
							maxDifficulty = players.get(i).getId().getDifficulty();
							ans = i;
						} else if (players.get(i).getId().getDifficulty() > maxDifficulty) {
							maxDifficulty = players.get(i).getId().getDifficulty();
							ans = i;
						}
					}
					
				}
			}
		}
		return ans;
	}
	
	public void endGame() {
		status = Consts.ENDGAME;
		int winnerId = winnerId();
		int i;
		for (i=0;i<players.size();i++) {
			List<Transaction> ts;
			if (i == winnerId) {
				ts = players.get(i).getAccount().receiveWinReward(players.get(i).getName());
			} else {
				ts = players.get(i).getAccount().receiveLoseReward(players.get(i).getName());
			}
			players.get(i).setReceivesFromTransaction(ts);
		}
	}
	
	public boolean gameEnds() {
		int[] arr = {1,4,8,7,7,6,5};
		int gameEndSize = arr[players.size()];
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getPlay().size() >= gameEndSize) return true;
		}
		return false;
	}
	
	public void moveWei(int x) {
		int oldWeiPos = weiPos;
		weiPos = weiPos+x;
		if (weiPos > Consts.MAXPOS) weiPos = Consts.MAXPOS;
		if (weiPos < Consts.MINPOS) weiPos = Consts.MINPOS;
		int newWeiPos = weiPos;
		logger.logMove(1, x, oldWeiPos, newWeiPos);
	}
	public void moveHan(int x) {
		int oldHanPos = hanPos;
		hanPos = hanPos+x;
		if (hanPos > Consts.MAXPOS) hanPos = Consts.MAXPOS;
		if (hanPos < Consts.MINPOS) hanPos = Consts.MINPOS;
		int newHanPos = hanPos;
		logger.logMove(0, x, oldHanPos, newHanPos);
	}
	public void addToExile(Card c) {
		exile.add(0,c);
	}
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	public boolean isLord(String username) {
		if (username.contentEquals(lord)) {
			return true;
		} else {
			return false;
		}
	}
	public Card topTomb() {
		if (tomb.size() == 0) {
			return new BlankSpaceCard();
		} else {
			return tomb.get(0);
		}
	}
	public void log(String s) {
		logger.log(s);
	}
	public void log(String name, Card c) {
		logger.logPlayCard(name,c);
	}
	public void log(String s, Card c, String i) {
		logger.log(s, c, i);
	}
	public void addToTomb(Card c) {
		tomb.add(0,c);
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
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getWeiPos() {
		return weiPos;
	}
	public void setWeiPos(int weiPos) {
		this.weiPos = weiPos;
	}
	public int getHanPos() {
		return hanPos;
	}
	public void setHanPos(int hanPos) {
		this.hanPos = hanPos;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getTavern() {
		return tavern;
	}
	public void setTavern(List<Card> tavern) {
		this.tavern = tavern;
	}
	public List<Card> getExile() {
		return exile;
	}
	public void setExile(List<Card> exile) {
		this.exile = exile;
	}
	public List<Card> getTomb() {
		return tomb;
	}
	public void setTomb(List<Card> tomb) {
		this.tomb = tomb;
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
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		players.add(p);
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
	
	List<Document> toCardDocumentList(List<Card> cards){
		List<Document> docs = new ArrayList<>();
		for (int i=0;i<cards.size();i++) {
			docs.add(cards.get(i).toDocument());
		}
		return docs;
	}
	
	public void updateBasicDB() {
		updateDB("status", status);
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("weiPos", weiPos);
		updateDB("hanPos", hanPos);
		updateDB("deck", toCardDocumentList(deck));
		updateDB("tavern", toCardDocumentList(tavern));
		updateDB("exile", toCardDocumentList(exile));
		updateDB("tomb", toCardDocumentList(tomb));
		updateDB("logger", logger.toDocument());
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("firstPlayer", firstPlayer);
		doc.append("curPlayer", curPlayer);
		doc.append("weiPos", weiPos);
		doc.append("hanPos", hanPos);
		doc.append("logger", logger.toDocument());
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		List<Document> dod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dod.add(deck.get(i).toDocument());
		}
		doc.append("deck", dod);
		List<Document> dot = new ArrayList<>();
		for (i=0;i<tavern.size();i++) {
			dot.add(tavern.get(i).toDocument());
		}
		doc.append("tavern", dot);
		List<Document> doe = new ArrayList<>();
		for (i=0;i<exile.size();i++) {
			doe.add(exile.get(i).toDocument());
		}
		doc.append("exile", doe);
		List<Document> doo = new ArrayList<>();
		for (i=0;i<tomb.size();i++) {
			doo.add(tomb.get(i).toDocument());
		}
		doc.append("tomb", doo);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		firstPlayer = doc.getInteger("firstPlayer", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		weiPos = doc.getInteger("weiPos", -1);
		hanPos = doc.getInteger("hanPos", -1);
		Document dol = (Document) doc.get("logger");
		logger.setFromDoc(dol);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			name = "player-" + name;
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setIndex(i);
			p.setBoard(this);
			p.setFromDoc(dop);
			players.add(p);
		}
		List<Document> dod = (List<Document>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<dod.size();i++) {
			Card c = CardFactory.makeCard(dod.get(i));
			c.setWhere(Consts.DECK);
			deck.add(c);
		}
		List<Document> dot = (List<Document>) doc.get("tavern");
		tavern = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Card c = CardFactory.makeCard(dot.get(i));
			c.setWhere(Consts.TAVERN);
			tavern.add(c);
		}
		List<Document> doe = (List<Document>) doc.get("exile");
		exile = new ArrayList<>();
		for (i=0;i<doe.size();i++) {
			Card c = CardFactory.makeCard(doe.get(i));
			c.setWhere(Consts.EXILE);
			exile.add(c);
		}
		List<Document> doo = (List<Document>) doc.get("tomb");
		tomb = new ArrayList<>();
		for (i=0;i<doo.size();i++) {
			Card c = CardFactory.makeCard(doo.get(i));
			c.setWhere(Consts.TOMB);
			tomb.add(c);
		}
	}
	
	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(status);
		entity.setCurPlayer(curPlayer);
		entity.setHanPos(hanPos);
		entity.setWeiPos(weiPos);
		entity.setNumDeck(deck.size());
		entity.setNumExile(exile.size());
		entity.setNumTomb(tomb.size());
		entity.setWinnerId(winnerId());
		entity.setLogger(logger.toLoggerEntity());
		int i,j;
		List<PlayerEntity> playerEntity = new ArrayList<>();
		List<CardEntity> myHand = new ArrayList<>();
		List<CardEntity> myJail = new ArrayList<>();
		List<Integer> myID = new ArrayList<>();
		int myIndex = -1;
		int phase = Consts.OFFTURN;
		int myDifficulty = -1;
		Player me = null;
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			//playerEntity.add(players.get(i).toPlayerEntity());
			if (name.contentEquals(p.getName())) {
				for (j=0;j<p.getHand().size();j++) {
					myHand.add(p.getHand().get(j).toCardEntity(p));
				}
				for (j=0;j<p.getJail().size();j++) {
					myJail.add(p.getJail().get(j).toCardEntity(p));
				}
				myID = p.getId().getFactions();
				myDifficulty = p.getId().getDifficulty();
				phase = p.getPhase();
				myIndex = i;
				me = p;
			}
		}
		for (i=0;i<players.size();i++) {
			playerEntity.add(players.get(i).toPlayerEntity(me));
		}
		entity.setPlayers(playerEntity);
		entity.setMyHand(myHand);
		entity.setMyJail(myJail);
		entity.setMyID(myID);
		entity.setMyDifficulty(myDifficulty);
		entity.setMyIndex(myIndex);
		entity.setPhase(phase);
		List<CardEntity> tavernEntity = new ArrayList<>();
		for (i=0;i<tavern.size();i++) {
			tavernEntity.add(tavern.get(i).toCardEntity(me));
		}
		entity.setTavern(tavernEntity);
		List<CardEntity> tombEntity = new ArrayList<>();
		for (i=0;i<tomb.size();i++) {
			tombEntity.add(tomb.get(i).toCardEntity());
		}
		if (tomb.size() == 0) {
			Card c = new BlankSpaceCard();
			tombEntity.add(c.toCardEntity());
		}
		entity.setTomb(tombEntity);
		//entity.setTopTomb(topTomb().toCardEntity(me));
		return entity;
	}
	
}
