package com.cosine.cosgame.oink;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.account.entity.AccountEntity;
import com.cosine.cosgame.oink.startups.Startups;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int game;
	int status;
	int firstPlayer;
	
	List<String> playerNames;
	
	Startups startups;
	
	MongoDBUtil dbutil;
	
	public Board() {
		startups = new Startups(this);
		
		playerNames = new ArrayList<>();
		
		game = Consts.UNDECIDED;
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public Document toDocument() {
		Document doc = new Document();
		
		if (game == Consts.STARTUPS) {
			doc = startups.toDocument();
		}
		
		doc.append("id", id);
		doc.append("game", game);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("playerNames", playerNames);
		doc.append("firstPlayer", firstPlayer);
		
		int i;
		List<Document> da = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Account a = new Account();
			a.getFromDB(playerNames.get(i));
			da.add(a.toDocument());
		}
		doc.append("accounts", da);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		game = doc.getInteger("game", -1);
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		playerNames = (List<String>) doc.get("playerNames");
		firstPlayer = doc.getInteger("firstPlayer", -1);
		
		if (game == Consts.STARTUPS) {
			startups.setFromDoc(doc);
		}
	}
	
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setGame(game);
		entity.setStatus(status);
		entity.setPlayerNames(playerNames);
		
		if (game == Consts.STARTUPS) {
			entity.setStartups(startups.toStartupsEntity(username));
		}
		
		int i;
		List<AccountEntity> aes = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Account a = new Account();
			a.getFromDB(playerNames.get(i));
			aes.add(a.toAccountEntity());
		}
		entity.setAccounts(aes);
		
		return entity;
	}
	
	public void addPlayer(String name) {
		playerNames.add(name);
	}
	
	public void addPlayerToDB(String name) {
		addPlayer(name);
		updateDB("playerNames", playerNames);
	}
	
	public void removePlayer(int x) {
		if (x>0 && x<playerNames.size()) {
			playerNames.remove(x);
		}
	}
	
	public void removePlayerFromDB(int x) {
		removePlayer(x);
		updateDB("playerNames", playerNames);
	}
	
	public void startGameUDB(List<Integer> settings) {
		int game = settings.get(Consts.SETTINGS_GAME);
		if (game>0 && game <= Consts.NUMGAMES) {
			this.game = game;
			this.firstPlayer = settings.get(Consts.SETTINGS_FIRSTPLAYER);
			this.status = Consts.INGAME;
			
			updateDB("game", this.game);
			updateDB("firstPlayer", this.firstPlayer);
			updateDB("status", this.status);
			
			if (this.game == Consts.STARTUPS) {
				startups.startGameUDB();
			}
		}
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
	
	public boolean isGame(int game) {
		return this.game == game;
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
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public Startups getStartups() {
		return startups;
	}
	public void setStartups(Startups startups) {
		this.startups = startups;
	}
}
