package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.gardenwar.base.*;
import com.cosine.cosgame.gardenwar.entity.BoardEntity;
import com.cosine.cosgame.gardenwar.entity.CardEntity;
import com.cosine.cosgame.gardenwar.entity.PlayerEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int firstPlayer;
	int curPlayer;
	int status;
	int roundCount;
	List<Player> players;
	List<Card> supplyDeck;
	List<Card> supply;
	List<Card> trash;
	List<Integer> settings;
	
	Logger logger;
	MongoDBUtil dbutil;	
	
	public Board() {
		players = new ArrayList<>();
		supplyDeck = new ArrayList<>();
		supply = new ArrayList<>();
		trash = new ArrayList<>();
		lord = "";
		logger = new Logger();
		
		String dbname = "gardenwar";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		status = Consts.INGAME;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).initialize();
		}
		Random rand = new Random();
		firstPlayer = rand.nextInt(players.size());
		curPlayer = firstPlayer;
		players.get(curPlayer).nextPhase();
		
		AllRes allRes = new AllRes();
		supplyDeck = allRes.getSupplyDeck();
		
		// TODO test cards here
		//supplyDeck.add(0, new CoffeeBean());
		
		supply = new ArrayList<>();
		for (i=0;i<Consts.SUPPLYSIZE;i++) {
			if (supplyDeck.size() == 0) break;
			Card c = supplyDeck.remove(0);
			supply.add(c);
		}
	}
	
	public Card getBasic(int x) {
		return AllRes.getBasic(x);
	}
	
	public Card popSupply(int x) {
		if (x>=0 && x<supply.size()) {
			Card card = supply.remove(x);
			if (supplyDeck.size() > 0) {
				Card c = supplyDeck.remove(0);
				supply.add(c);
			}
			return card;
		} else {
			return null;
		}
	}
	
	public void nextPlayer() {
		curPlayer = curPlayer+1;
		if (curPlayer >= players.size()) {
			curPlayer = curPlayer - players.size();
		}
		players.get(curPlayer).nextPhase();
	}
	
	public void log(String s) {
		logger.log(s);
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getSupplyDeck() {
		return supplyDeck;
	}
	public void setSupplyDeck(List<Card> supplyDeck) {
		this.supplyDeck = supplyDeck;
	}
	public List<Card> getSupply() {
		return supply;
	}
	public void setSupply(List<Card> supply) {
		this.supply = supply;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public List<Integer> getSettings() {
		return settings;
	}
	public void setSettings(List<Integer> settings) {
		this.settings = settings;
	}
	public List<Card> getTrash() {
		return trash;
	}
	public void setTrash(List<Card> trash) {
		this.trash = trash;
	}
	List<Document> toCardDocumentList(List<Card> cards){
		List<Document> docs = new ArrayList<>();
		for (int i=0;i<cards.size();i++) {
			docs.add(cards.get(i).toDocument());
		}
		return docs;
	}
	List<Card> toCardList(List<Document> docs){
		List<Card> cards = new ArrayList<>();
		for (int i=0;i<docs.size();i++) {
			Card c = CardFactory.makeCard(docs.get(i));
			cards.add(c);
		}
		return cards;
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
	
	public void updateBasicDB() {
		updateDB("status", status);
		updateDB("curPlayer", curPlayer);
		updateDB("logs", logger.getLogs());
		updateDB("supplyDeck", toCardDocumentList(supplyDeck));
		updateDB("supply", toCardDocumentList(supply));
		updateDB("trash", toCardDocumentList(trash));
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("firstPlayer", firstPlayer);
		doc.append("curPlayer", curPlayer);
		doc.append("status", status);
		doc.append("roundCount", roundCount);
		doc.append("supplyDeck", toCardDocumentList(supplyDeck));
		doc.append("supply", toCardDocumentList(supply));
		doc.append("trash", toCardDocumentList(trash));
		doc.append("logs", logger.getLogs());
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			String name = "player-" + players.get(i).getName();
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		firstPlayer = doc.getInteger("firstPlayer", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		status = doc.getInteger("status", -1);
		roundCount = doc.getInteger("roundCount", -1);
		supplyDeck = toCardList((List<Document>)doc.get("supplyDeck"));
		supply = toCardList((List<Document>)doc.get("supply"));
		trash = toCardList((List<Document>)doc.get("trash"));
		List<String> logs = (List<String>) doc.get("logs");
		logger.setLogs(logs);
		List<List<Document>> dob = (List<List<Document>>) doc.get("basicPiles");
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
	}
	
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(status);
		entity.setCurPlayer(curPlayer);
		int i;
		List<PlayerEntity> playerEntities = new ArrayList<>();
		List<CardEntity> myHand = new ArrayList<>();
		List<CardEntity> myEquip = new ArrayList<>();
		List<CardEntity> curPlayerPlay = new ArrayList<>();
		List<Boolean> curPlayerCanBuy = new ArrayList<>();
		int myIndex = -1;
		int phase = Consts.OFFTURN;
		int curPlayerSun = 0;
		int curPlayerPea = 0;
		boolean canAutoPlay = true;
		int askType = 0;
		int askSubType = 0;
		String askMsg = "";
		List<CardEntity> baseCards = new ArrayList<>();
		for (i=0;i<4;i++) {
			baseCards.add(AllRes.getBasic(i).toCardEntity());
		}
		List<CardEntity> supplyCards = new ArrayList<>();
		for (i=0;i<supply.size();i++) {
			supplyCards.add(supply.get(i).toCardEntity());
		}
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			playerEntities.add(players.get(i).toPlayerEntity());
			int j;
			if (p.getName().contentEquals(username)) {
				myIndex = i;
				phase = p.getPhase();
				for (j=0;j<p.getHand().size();j++) {
					Card c = p.getHand().get(j);
					myHand.add(c.toCardEntity());
					if (c.isAutoplay() == false) {
						canAutoPlay = false;
					}
				}
				if (p.getHand().size()==0) canAutoPlay = false;
				
				for (j=0;j<p.getEquip().size();j++) {
					Card c = p.getEquip().get(j);
					myEquip.add(c.toCardEntity());
				}
				
				askType = p.getAskType();
				askSubType = p.getAskSubType();
				askMsg = p.getAskMsg();
			}
			if (i == curPlayer) {
				curPlayerSun = p.getSun();
				curPlayerPea = p.getAtk();
				curPlayerCanBuy = p.getCanBuy();
				for (j=0;j<p.getPlay().size();j++) {
					curPlayerPlay.add(p.getPlay().get(j).toCardEntity());
				}
			}
		}
		entity.setCanAutoPlay(canAutoPlay);
		entity.setMyIndex(myIndex);
		entity.setPhase(phase);
		entity.setMyHand(myHand);
		entity.setMyEquip(myEquip);
		entity.setCurPlayerSun(curPlayerSun);
		entity.setCurPlayerPea(curPlayerPea);
		entity.setCurPlayerPlay(curPlayerPlay);
		entity.setCurPlayerCanBuy(curPlayerCanBuy);
		entity.setPlayers(playerEntities);
		entity.setBaseCards(baseCards);
		entity.setSupply(supplyCards);
		entity.setAskMsg(askMsg);
		entity.setAskType(askType);
		entity.setAskSubType(askSubType);
		entity.setLogs(logger.getLogs());
		return entity;
	}
}
