package com.cosine.cosgame.oink;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.startups.Startups;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int game;
	int status;
	
	List<String> playerNames;
	
	Startups startups;
	
	MongoDBUtil dbutil;
	
	public Board() {
		startups = new Startups();
		
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
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		game = doc.getInteger("game", -1);
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		playerNames = (List<String>) doc.get("playerNames");
		
		if (game == Consts.STARTUPS) {
			startups.setFromDoc(doc);
		}
	}
	
	public void addPlayer(String name) {
		playerNames.add(name);
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
}
