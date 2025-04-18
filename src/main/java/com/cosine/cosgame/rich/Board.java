package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.InJail;
import com.cosine.cosgame.rich.builder.MapBuilder;
import com.cosine.cosgame.rich.entity.BoardEntity;
import com.cosine.cosgame.rich.entity.CardEntity;
import com.cosine.cosgame.rich.entity.PlaceEntity;
import com.cosine.cosgame.rich.entity.PlayerEntity;
import com.cosine.cosgame.rich.gta.places.Ward;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	protected String id;
	protected String lord;
	protected String broadcastMsg;
	protected String broadcastImg;
	
	protected int status;
	protected int curCardId;
	protected int curPlayer;
	protected int round;
	protected int lastRolled;
	protected int lastFateId;
	
	protected Map map;
	protected List<String> playerNames;
	protected List<Player> players;
	protected List<String> ses;
	protected int sesPlayer;
	
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
		doc.append("boardcastMsg", broadcastMsg);
		doc.append("broadcastImg", broadcastImg);
		doc.append("logs", logger.getLogs());
		doc.append("ses", ses);
		doc.append("sesPlayer", sesPlayer);
		String mapName = "-";
		if (map != null) mapName = map.getName();
		doc.append("mapName", mapName);
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
		broadcastMsg = doc.getString("broadcastMsg");
		broadcastImg = doc.getString("broadcastImg");
		List<Integer> settingsList = (List<Integer>)doc.get("settings");
		settings = new Settings(settingsList);
		playerNames = (List<String>)doc.get("playerNames");
		ses = (List<String>) doc.get("ses");
		sesPlayer = doc.getInteger("sesPlayer", -1);
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
		int i,j;
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(status);
		entity.setMap(map.toMapEntity(username));
		entity.setSettings(settings.toSettingsEntity());
		entity.setLastRolled(lastRolled);
		entity.setRound(round);
		entity.setCurPlayer(curPlayer);
		entity.setBroadcastImg(broadcastImg);
		entity.setBroadcastMsg(broadcastMsg);
		entity.setEndCondition(settings.getEndCondition());
		
		String lastRolledDisplay = "";
		if (lastRolled<10) {
			lastRolledDisplay = "一个" + lastRolled;
		} else if (lastRolled>10 && lastRolled<100) {
			int r1 = lastRolled/10;
			int r2 = lastRolled%10;
			lastRolledDisplay = "" + r1 + "和" + r2;
		}
		entity.setLastRolledDisplay(lastRolledDisplay);
		
		HashMap<String, String> broadcastImgStyle = new HashMap<>();
		if (broadcastImg != null && broadcastImg.length() > 0) {
			broadcastImgStyle.put("background-image", "url(/image/Rich/" + broadcastImg + ".png)");
		}
		entity.setBroadcastImgStyle(broadcastImgStyle);
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
				entity.setGoingToJail(p.isGoingToJail());
				
				if (sesPlayer == Consts.SES_ALLPLAYERS || sesPlayer == i) {
					entity.setSes(ses);
				} else {
					entity.setSes(new ArrayList<>());
				}
				
				
				if (map.getPlace(p.getPlaceIndex()) != null) {
					if (p.isInJail()) {
						InJail inJail = new InJail(0,"监狱");
						inJail.setBoard(this);
						entity.setMyCurrentPlace(inJail.toPlaceEntity(username));
					} else if (p.isInWard()) {
						Ward ward = new Ward(0,"病房");
						ward.setBoard(this);
						entity.setMyCurrentPlace(ward.toPlaceEntity(username));
					}
					
					else {
						entity.setMyCurrentPlace(map.getPlace(p.getPlaceIndex()).toPlaceEntity(username));
					}
					
					entity.setMyBuffs(p.getBuff().getBuffs());
					
				}
				entity.setMyLandMsg(p.myLandMsg());
				entity.setInJail(p.isInJail());
				entity.setJailRound(p.getJailRound());
				entity.setMySalary(p.getSalary());
				entity.setMyStar(p.getStar());
				entity.setMyHp(p.getHp());
				
				List<CardEntity> handEntity = new ArrayList<>();
				for (j=0;j<p.getHand().size();j++) {
					handEntity.add(p.getHand().get(j).toCardEntity());
				}
				entity.setMyHand(handEntity);
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
		ses = new ArrayList<>();
		broadcastMsg = "";
		broadcastImg = "";
		
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
	
	public void roll(Player p, int n) {
		int result = 0;
		if (p != null && p.getBuff().getNextRoll() != -1) {
			result = p.getBuff().getNextRoll();
			p.getBuff().clearNextRoll();
		} else {
			Random rand = new Random();
			for (int i=0;i<n;i++) {
				int t = rand.nextInt(60000);
				int x = t%6+1;
				result = result*10+x;
			}
			
			// TODO: edit dice result here;
			//result = 15;
		}
		
		
		lastRolled = result;
	}
	
	public void roll(Player p) {
		roll(p, 1);
	}
	
	// GTA related
	public void wardCheck() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getHp() == 0 && players.get(i).isInWard() == false) {
				players.get(i).goToWard();
			}
		}
	}
	// End GTA related
	
	public void nextPlayer() {
		// Step 1: end the turn for the current player
		players.get(curPlayer).setPhase(Consts.PHASE_OFFTURN);
		
		// Step 2: reset fate id for display purposes
		lastFateId = 0;
		
		// Step 3: GTA related, send all user with 0 hp to the ward
		if (settings.getUseGTA() == 1) {
			wardCheck();
		}
		
		// Step 4: GTA related, minus all related buffs
		players.get(curPlayer).getBuff().turnEndMinus();
		
		// Step 5: find the next player and potentially start round
		curPlayer = (curPlayer+1)%players.size();
		if (curPlayer == settings.getFirstPlayer()) {
			logger.logRoundEndDivider();
			if (gameEnds()) {
				endGame();
			} else {
				newRound();
				players.get(curPlayer).startTurn();
			}
		} else {
			if (gameEnds()) {
				logger.logAboutEnd();
			}
			players.get(curPlayer).startTurn();
		}
	}
	public void newRound() {
		// Step 1: add and log round #
		round++;
		logger.logRoundStart(round);
		
		// Step 2: GTA related, deal 1 card every 5 rounds
		int i;
		if (round%5 == 0) {
			logger.log("所有不在监狱的玩家获得一张牌且通缉值-1");
			for (i=0;i<players.size();i++) {
				if (players.get(i).isInJail() == false) {
					players.get(i).addRandomCard();
					players.get(i).loseStar(1);
				}			
			}
		}
		
	}
	public boolean gameEnds() {
		int i, numAlive;
		boolean ans = false;
		int ec = settings.getEndCondition();
		if (ec < 10) { // alive player related
			numAlive = 0;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getMoney() >= 0) {
					numAlive++;
				}
			}
			if (ec == 0) {
				if (numAlive < 2) {
					ans = true;
				}
			} else if (ec == 1) {
				if (numAlive < players.size()) {
					ans = true;
				}
			}
		} else if (ec<1000){ // num rounds related
			if (round>=ec) {
				ans = true;
			}
		} else { // money related
			for (i=0;i<players.size();i++) {
				if (players.get(i).getMoney() >= ec) {
					ans = true;
				}
			}
		}
		return ans;
	}
	public void endGame() {
		// Step 1: set status
		status = Consts.ENDGAME;
		logger.logEndGame();
	}
	
	// Actual Operations
	public void startGameUDB(List<Integer> settingsList) {
		// Step 1: initialize Map, settings and players
		this.status = Consts.INGAME;
		
		settings = new Settings(settingsList);
		map = MapBuilder.genMap(settings);
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).startGame();
		}
		
		// Step 2: game start broadcast msg
		setBroadcastImg("gameStart");
		setBroadcastMsg("游戏开始！");
		
		// Step 3: start round
		newRound();
		players.get(curPlayer).startTurn();
		
		// Last Step: update DB
		updateDB("settings", settings.getSettings());
		updateBasicDB();
		updatePlayers();
		updateDB("mapName", map.getName());
	}
	
	public void buttonPressUDB(String username, int option) {
		ses = new ArrayList<>();
		sesPlayer = -1;
		Player p = getPlayerByName(username);
		if (p == null) return;
		if (p.getPhase() == Consts.PHASE_OFFTURN) {
			
		} else if (p.getPhase() == Consts.PHASE_ROLL) {
			p.phaseRoll(option);
		} else if (p.getPhase() == Consts.PHASE_MOVE) {
			p.phaseMove(option);
		} else if (p.getPhase() == Consts.PHASE_RESOLVE) {
			p.phaseResolve(option);
		} else if (p.getPhase() == Consts.PHASE_ESCAPE) {
			p.phaseEscape(option);
		} else if (p.getPhase() == Consts.PHASE_UTILITY) {
			p.phaseUtility(option);
		} else if (p.getPhase() == Consts.PHASE_STATION) {
			p.phaseStation(option);
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
		updateDB("broadcastMsg",broadcastMsg);
		updateDB("broadcastImg",broadcastImg);
		updateDB("logs", logger.getLogs());
		updateDB("ses", ses);
		updateDB("sesPlayer", sesPlayer);
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
	public void addSes(String se) {
		ses.add(se);
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
	public String getBroadcastMsg() {
		return broadcastMsg;
	}
	public void setBroadcastMsg(String broadcastMsg) {
		this.broadcastMsg = broadcastMsg;
	}
	public String getBroadcastImg() {
		return broadcastImg;
	}
	public void setBroadcastImg(String broadcastImg) {
		this.broadcastImg = broadcastImg;
	}
	public List<String> getSes() {
		return ses;
	}
	public void setSes(List<String> ses) {
		this.ses = ses;
	}
	public int getSesPlayer() {
		return sesPlayer;
	}
	public void setSesPlayer(int sesPlayer) {
		this.sesPlayer = sesPlayer;
	}
}
