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
	int status;
	int firstPlayer;
	int curPlayer;
	List<Integer> settings;
	List<Player> players;
	
	GameUtil gameUtil;
	
	MongoDBUtil dbutil;
	
	public Board() {
		settings = new ArrayList<>();
		players = new ArrayList<>();
		
		lord = "";
		gameUtil = new GameUtil();
		gameUtil.setBoard(this);
		
		String dbname = "pokerworld";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	public void startGame() {
		status = Consts.INGAME;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setInnerId(i);
		}
		gameUtil.newGame();
	}
	public boolean isLord(String username) {
		if (username == null) return false;
		if (lord == null) return true;
		if (lord.contentEquals(username)) return true;
		return false;
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
	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
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
		dbutil.update("id", id, "cards", gameUtil.toRawCards());
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
		doc.append("firstPlayer", firstPlayer);
		doc.append("curPlayer", curPlayer);
		doc.append("settings", settings);
		doc.append("cards", gameUtil.toRawCards());
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
		firstPlayer = doc.getInteger("firstPlayer", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		settings = (List<Integer>) doc.get("settings");
		List<String> rawCards = (List<String>) doc.get("cards");
		gameUtil.buildCards(rawCards);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		int i;
		List<PlayerEntity> playerEntities = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerEntities.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				Player p = players.get(i);
				entity.setMyCards(p.getMyRawCardsAfterPlay());
			}
		}
		entity.setPlayers(playerEntities);
		return entity;
	}
	
}
