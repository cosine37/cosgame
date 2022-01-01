package com.cosine.cosgame.propnight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.propnight.entity.BoardEntity;
import com.cosine.cosgame.propnight.entity.PlayerEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int status;
	int phase;
	
	List<Player> players;
	List<Place> places;
	int humanMark;
	int ghostMark;
	List<Integer> placeSupply;
	List<Card> humanDeck;
	List<Card> ghostDeck;
	
	MongoDBUtil dbutil = new MongoDBUtil();
	
	public Board() {
		players = new ArrayList<>();
		places = new ArrayList<>();
		placeSupply = new ArrayList<>();
		humanDeck = new ArrayList<>();
		ghostDeck = new ArrayList<>();
		
		String dbname = "propnight";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		status = Consts.ROLEASSIGNMENT;
		Random rand = new Random();
		int x = rand.nextInt(players.size());
		setGhostPlayer(x);
	}
	
	public void initialize() {
		status = Consts.INGAME;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).initialize();
		}
		phase = 1;
	}
	
	public void setGhostPlayer(int x) {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setRole(Consts.HUMAN);
		}
		players.get(x).setRole(Consts.GHOST);
	}
	
	public void setGhostPlayer(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setRole(Consts.HUMAN);
			if (players.get(i).getName().contentEquals(name)) {
				players.get(i).setRole(Consts.GHOST);
			}
		}
	}
	
	public void endTurn() {
		int i;
		for (i=0;i<places.size();i++) {
			places.get(i).cleanGhostToken();
			places.get(i).cleanHuman();
		}
		for (i=0;i<players.size();i++) {
			players.get(i).endTurn();
		}
	}
	
	public boolean allHumanChosePlace() {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getRole() == Consts.HUMAN) {
				if (players.get(i).getPlaceThisTurn().size() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void nextPhase() {
		if (phase == 1){
			phase = 2;
		}
	}
	
	public void moveGhostMark(int x) {
		ghostMark = ghostMark + x;
	}
	
	public void moveHumanMark(int x) {
		humanMark = humanMark + x;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public int getHumanMark() {
		return humanMark;
	}
	public void setHumanMark(int humanMark) {
		this.humanMark = humanMark;
	}
	public int getGhostMark() {
		return ghostMark;
	}
	public void setGhostMark(int ghostMark) {
		this.ghostMark = ghostMark;
	}
	public List<Integer> getPlaceSupply() {
		return placeSupply;
	}
	public void setPlaceSupply(List<Integer> placeSupply) {
		this.placeSupply = placeSupply;
	}
	public List<Card> getHumanDeck() {
		return humanDeck;
	}
	public void setHumanDeck(List<Card> humanDeck) {
		this.humanDeck = humanDeck;
	}
	public List<Card> getGhostDeck() {
		return ghostDeck;
	}
	public void setGhostDeck(List<Card> ghostDeck) {
		this.ghostDeck = ghostDeck;
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
		updateDB("phase", phase);
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("phase", phase);
		doc.append("humanMark", humanMark);
		doc.append("ghostMark", ghostMark);
		doc.append("placeSupply", placeSupply);
		int i;
		List<Integer> placeLs = new ArrayList<>();
		for (i=0;i<places.size();i++) {
			placeLs.add(places.get(i).getId());
		}
		doc.append("places", placeLs);
		List<Document> lohd = new ArrayList<>();
		for (i=0;i<humanDeck.size();i++) {
			lohd.add(humanDeck.get(i).toDocument());
		}
		doc.append("humanDeck", lohd);
		List<Document> logd = new ArrayList<>();
		for (i=0;i<ghostDeck.size();i++) {
			logd.add(ghostDeck.get(i).toDocument());
		}
		doc.append("ghostDeck", logd);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			doc.append("player-" + name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		phase = doc.getInteger("phase", -1);
		humanMark = doc.getInteger("humanMark", -1);
		ghostMark = doc.getInteger("ghostMark", -1);
		placeSupply = (List<Integer>) doc.get("placeSupply");
		int i;
		List<Integer> placeLs = (List<Integer>) doc.get("places");
		places = new ArrayList<>();
		for (i=0;i<placeLs.size();i++) {
			places.add(PlaceFactory.makePlace(placeLs.get(i)));
		}
		List<Document> lohd = (List<Document>) doc.get("humanDeck");
		humanDeck = new ArrayList<>();
		for (i=0;i<lohd.size();i++) {
			Card c = CardFactory.makeCard(lohd.get(i));
			humanDeck.add(c);
		}
		List<Document> logd = (List<Document>) doc.get("ghostDeck");
		ghostDeck = new ArrayList<>();
		for (i=0;i<logd.size();i++) {
			Card c = CardFactory.makeCard(logd.get(i));
			ghostDeck.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			Player p = new Player();
			Document d = (Document) doc.get("player-" + name);
			p.setBoard(this);
			p.setFromDoc(d);
			players.add(p);
		}
	}

	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setGhostMark(ghostMark);
		entity.setHumanMark(humanMark);
		entity.setStatus(status);
		entity.setPhase(phase);
		int i;
		List<PlayerEntity> lp = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			lp.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				entity.setMyIndex(i);
				entity.setMyRole(players.get(i).getRole());
				entity.setMyPlaceThisTurn(players.get(i).getPlaceThisTurn());
				entity.setMyNumPlaceNextTurn(players.get(i).getNumPlaceNextTurn());
			}
		}
		entity.setPlayers(lp);
		return entity;
	}
	
}
