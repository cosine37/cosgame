package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.rich.builder.MapBuilder;
import com.cosine.cosgame.rich.entity.BoardEntity;
import com.cosine.cosgame.rich.entity.PlayerEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	protected String id;
	protected String lord;
	
	protected int status;
	protected int curCardId;
	protected int curPlayer;
	protected int round;
	protected int lastRolled;
	protected int lastFateId;
	
	protected Map map;
	protected List<String> playerNames;
	protected List<Player> players;
	
	protected Settings settings;
	protected Logger logger;
	protected MongoDBUtil dbutil;

	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("id",id);
		doc.append("lord",lord);
		doc.append("status",status);
		doc.append("curCardId",curCardId);
		doc.append("curPlayer",curPlayer);
		doc.append("round",round);
		doc.append("map",map.toDocument());
		doc.append("settings",settings.getSettings());
		doc.append("playerNames",playerNames);
		doc.append("lastRolled", lastRolled);
		doc.append("lastFateId", lastFateId);
		doc.append("logs", logger.getLogs());
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
		round = doc.getInteger("round",0);
		lastRolled = doc.getInteger("lastRolled", 0);
		lastFateId = doc.getInteger("lastFateId", 0);
		List<Integer> settingsList = (List<Integer>)doc.get("settings");
		settings = new Settings(settingsList);
		playerNames = (List<String>)doc.get("playerNames");
		List<String> logs = (List<String>) doc.get("logs");
		logger = new Logger(logs);
		
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
		
		Document mapDoc = (Document) doc.get("map");
		map = new Map();
		map.setBoard(this);
		map.setFromDoc(mapDoc);
		
	}
	
	public BoardEntity toBoardEntity(String username) {
		int i;
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(status);
		entity.setMap(map.toMapEntity());
		entity.setSettings(settings.toSettingsEntity());
		entity.setLastRolled(lastRolled);
		entity.setRound(round);
		entity.setCurPlayer(curPlayer);
		entity.setLogs(logger.getLogs());
		Fate fate = Factory.genFate(lastFateId);
		if (fate != null) {
			String s = "";
			for (i=0;i<fate.getConversation().length();i++) {
				if (fate.getConversation().charAt(i) == 'p') {
					s = s+players.get(curPlayer);
				} else {
					s = s+fate.getConversation().charAt(i);
				}
			}
			entity.setFateMsg(s);
		} else {
			entity.setFateMsg("");
		}
		
		List<PlayerEntity> pes = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			pes.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				Player p = players.get(i);
				entity.setPhase(p.getPhase());
				entity.setMyOptions(p.getOptions());
				entity.setMyNextPlace(p.myNextPlaceName());
				entity.setMyCurrentPlace(p.myCurrentPlaceName());
				entity.setMyLandMsg(p.myLandMsg());
				entity.setInJail(p.isInJail());
				entity.setJailRound(p.getJailRound());
			}
		}
		entity.setPlayers(pes);
		return entity;
	}
	
	public Board() {
		map = new Map();
		settings = new Settings();
		logger = new Logger();
		players = new ArrayList<>();
		playerNames = new ArrayList<>();
		
		String dbname = "rich";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void putPlayerOnPlace(Player player, Place place) {
		place.addPlayerOn(player);
	}
	
	public void putPlayerOnPlace(Player p, int x) {
		Place place = map.getPlace(x);
		place.addPlayerOn(p);
	}
	
	public void roll(int n) {
		int result = 0;
		Random rand = new Random();
		for (int i=0;i<n;i++) {
			int t = rand.nextInt(60000);
			int x = t%6+1;
			result = result+x;
		}
		
		// TODO: edit dice result here;
		//result = 15;
		
		lastRolled = result;
	}
	
	public void roll() {
		roll(1);
	}
	public void nextPlayer() {
		// Step 1: end the turn for the current player
		players.get(curPlayer).setPhase(Consts.PHASE_OFFTURN);
		
		// Step 2: reset fate id for display purposes
		lastFateId = 0;
		
		// Step 3: find the next player and potentially start round
		curPlayer = (curPlayer+1)%players.size();
		if (curPlayer == settings.getFirstPlayer()) {
			logger.logRoundEndDivider();
			newRound();
		}
		players.get(curPlayer).startTurn();
	}
	public void newRound() {
		round++;
		logger.logRoundStart(round);
	}
	
	// Actual Operations
	public void startGameUDB(List<Integer> settingsList) {
		// Step 1: initialize Map, settings and players
		this.status = Consts.INGAME;
		map = MapBuilder.genTestMap();
		settings = new Settings(settingsList);
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).startGame();
		}
		
		// Step 2: start round
		newRound();
		players.get(curPlayer).startTurn();
		
		// Last Step: update DB
		updateDB("settings", settings.getSettings());
		updateBasicDB();
		updatePlayers();
	}
	
	public void buttonPressUDB(String username, int option) {
		Player p = getPlayerByName(username);
		if (p == null) return;
		if (p.getPhase() == Consts.PHASE_OFFTURN) {
			
		} else if (p.getPhase() == Consts.PHASE_ROLL) {
			p.phaseRoll(option);
		} else if (p.getPhase() == Consts.PHASE_MOVE) {
			p.phaseMove(option);
		} else if (p.getPhase() == Consts.PHASE_RESOLVE) {
			p.phaseResolve(option);
		}
		updateBasicDB();
		updatePlayers();
	}
	
	// End Actual Operations
	
	public void updateBasicDB() {
		updateDB("status", this.status);
		updateDB("lastRolled", this.lastRolled);
		updateDB("lastFateId", this.lastFateId);
		updateDB("map", map.toDocument());
		updateDB("round", round);
		updateDB("curPlayer", curPlayer);
		updateDB("logs", logger.getLogs());
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
		updatePlayers();
	}
	
	public boolean hasPlayer(String name) {
		for (int i=0;i<playerNames.size();i++) {
			if (playerNames.get(i).contentEquals(name)) {
				return true;
			}
		}
		return false;
	}
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
	public int getFirstPlayer() {
		return settings.getFirstPlayer();
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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
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
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	public int getLastRolled() {
		return lastRolled;
	}
	public void setLastRolled(int lastRolled) {
		this.lastRolled = lastRolled;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public int getLastFateId() {
		return lastFateId;
	}
	public void setLastFateId(int lastFateId) {
		this.lastFateId = lastFateId;
	}
}
