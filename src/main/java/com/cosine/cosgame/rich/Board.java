package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.entity.BoardEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	protected String id;
	protected String lord;
	
	protected int status;
	protected int curCardId;
	protected int curPlayer;
	protected int firstPlayer;
	protected int round;
	protected int targetMoney;
	protected int mode;
	
	protected int height;
	protected int width;
	
	protected Map map;
	protected List<Integer> settings;
	protected List<String> playerNames;
	protected List<Player> players;
	
	protected MongoDBUtil dbutil;

	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("id",id);
		doc.append("lord",lord);
		doc.append("status",status);
		doc.append("curCardId",curCardId);
		doc.append("curPlayer",curPlayer);
		doc.append("firstPlayer",firstPlayer);
		doc.append("round",round);
		doc.append("targetMoney",targetMoney);
		doc.append("height",height);
		doc.append("width",width);
		doc.append("map",map.toDocument());
		doc.append("settings",settings);
		doc.append("playerNames",playerNames);
		doc.append("mode", mode);
		for (i=0;i<players.size();i++){
			players.get(i).setIndex(i);
			String n = "player-" + players.get(i).getName();
			doc.append(n, players.get(i).toDocument());
		}
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status",0);
		curCardId = doc.getInteger("curCardId",0);
		curPlayer = doc.getInteger("curPlayer",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		round = doc.getInteger("round",0);
		targetMoney = doc.getInteger("targetMoney",0);
		height = doc.getInteger("height",0);
		width = doc.getInteger("width",0);
		settings = (List<Integer>)doc.get("settings");
		playerNames = (List<String>)doc.get("playerNames");
		mode = doc.getInteger("mode", 0);
		
		Document mapDoc = (Document) doc.get("map");
		map = new Map();
		map.setFromDoc(mapDoc);
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++){
			String n = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(n);
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
		entity.setMode(mode);
		entity.setStatus(status);
		return entity;
	}
	
	public Board() {
		map = new Map();
		players = new ArrayList<>();
		playerNames = new ArrayList<>();
		
		String dbname = "rich";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		
	}
	
	public void putPlayerOnPlace(Player player, Place place) {
		place.addPlayerOn(player);
	}
	
	public void putPlayerOnPlace(Player p, int x) {
		Place place = map.getPlace(x);
		place.addPlayerOn(p);
	}
	
	public int getSalary() {
		return 0;
	}
	
	// Actual Operations
	public void startGameUDB(List<Integer> settings) {
		this.status = Consts.INGAME;
		this.mode = 0;
		if (settings.size()>0) {
			this.mode = settings.get(0);
		}
		updateDB("status", this.status);
		updateDB("mode", this.mode);
	}
	
	// End Actual Operations
	
	
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
	
	public void addPlayer(String name) {
		playerNames.add(name);
		Player p = new Player();
		p.setName(name);
		p.setBoard(this);
		players.add(p);
	}
	
	public void addPlayerToDB(String name) {
		addPlayer(name);
		updateDB("playerNames", playerNames);
	}
	
	public boolean hasPlayer(String name) {
		for (int i=0;i<playerNames.size();i++) {
			if (playerNames.get(i).contentEquals(name)) {
				return true;
			}
		}
		return false;
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
	public boolean isLord(String name) {
		return lord.contentEquals(name);
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
	public int getCurCardId() {
		return curCardId;
	}
	public void setCurCardId(int curCardId) {
		this.curCardId = curCardId;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTargetMoney() {
		return targetMoney;
	}
	public void setTargetMoney(int targetMoney) {
		this.targetMoney = targetMoney;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public List<Integer> getSettings() {
		return settings;
	}
	public void setSettings(List<Integer> settings) {
		this.settings = settings;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
}
