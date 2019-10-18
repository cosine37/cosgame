package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Game {
	
	MongoDBUtil dbutil;
	
	public static final int INACTIVE = 0;
	public static final int INGAME = 1;
	public static final int ENDGAME = 2;
	
	String id;
	int status;
	List<Player> players;
	String lord;
	
	int round;
	int step;
	
	public Game() {
		players = new ArrayList<>();
		status = INACTIVE;
		round = 0;
		step = 0;
		id = "";
		lord = "";
		
		String dbname = "xtb";
		String col = "game";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void newGame(String lord) {
		Date date= new Date();
		id = Long.toString(date.getTime());
		players = new ArrayList<>();
		this.lord = lord;
		Player p = new Player(lord);
		players.add(p);
	}
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void addPlayer(String name) {
		Player p = new Player(name);
		players.add(p);
	}
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public void judge() {
		if (status == INGAME) {
			int i;
			int maxPower = 0;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getCurMove().getPower() > maxPower) {
					maxPower = players.get(i).getCurMove().getPower();
				}
			}
			for (i=0;i<players.size();i++) {
				if (players.get(i).getCurMove().getDefence() < maxPower) {
					players.get(i).die();
				}
			}
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

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("step", step);
		List<Document> dop = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			Document d = new Document();
			d.append("name", players.get(i).getName());
			dop.add(d);
			doc.append("player_" + players.get(i).getName(), players.get(i).toDocument());
		}
		doc.append("playernames", dop);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status");
		round = doc.getInteger("round");
		step = doc.getInteger("step");
		players = new ArrayList<>();
		List<Document> dop = (List<Document>)doc.get("playernames");
		for (int i=0;i<dop.size();i++) {
			String name = dop.get(i).getString("name");
			Player p = new Player();
			p.setFromDoc((Document)doc.get("player_" + name));
			players.add(p);
		}
	}
	
	public void storeGameToDB() {
		Document doc = toDocument();
		System.out.println(doc.toJson());
		dbutil.insert(doc);
	}
	
	public void getGameFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public void updateDB(String key, Document value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updatePlayerDB(String name, Player p) {
		updateDB(name,p.toDocument());
	}
}
