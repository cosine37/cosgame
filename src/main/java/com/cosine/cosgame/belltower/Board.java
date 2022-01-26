package com.cosine.cosgame.belltower;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	int status;
	int phase;
	int numDay;
	
	List<Player> players;
	Script script;
	
	MongoDBUtil dbutil;
	
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
	public int getNumDay() {
		return numDay;
	}
	public void setNumDay(int numDay) {
		this.numDay = numDay;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Script getScript() {
		return script;
	}
	public void setScript(Script script) {
		this.script = script;
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
	
	public void updateBasicDB() {
		updateDB("phase", phase);
		updateDB("status", status);
		updateDB("numDay", numDay);
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
		doc.append("phase", phase);
		doc.append("script", script.getId());
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		phase = doc.getInteger("phase", -1);
		int scriptId = doc.getInteger("script", -1);
		script = ScriptFactory.makeScript(scriptId);
		List<String> playerNames = (List<String>) doc.get("playerNames");
		int i;
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			name = "player-" + name;
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
		}
	}
}
