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
	
	Logs logs;
	
	public Game() {
		players = new ArrayList<>();
		status = INACTIVE;
		round = 0;
		step = 0;
		id = "";
		lord = "";
		logs = new Logs();
		
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
	
	public void addBot() {
		Player p = new Player();
		p.botSetup();
		players.add(p);
	}
	
	public void addBotToDB() {
		Player p = new Player();
		p.botSetup();
		players.add(p);
		updatePlayernamesDB();
		updatePlayerDB(p);
	}
	
	public void kickFromDB(String kickedName) {
		if (kickedName.equals(lord)) {
			
		} else {
			dbutil.removeKey("id", id, "player_" + kickedName);
			for (int i=0;i<players.size();i++) {
				if (players.get(i).getName().equals(kickedName)) {
					players.remove(i);
					break;
				}
			}
			updatePlayernamesDB();
		}
	}
	
	public void start() {
		status = INGAME;
		for (int i=0;i<players.size();i++) {
			players.get(i).setStatus(Player.ALIVE);
		}
		round = 0;
		newRound();
	}
	
	public void newRound() {
		round = round+1;
		step = 1;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getStatus() == Player.DEAD) continue;
			players.get(i).setEnergy(0);
			players.get(i).setBi(1);
		}
		logs.logCount(round, step);
	}
	
	public void nextStep() {
		step = step + 1;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getStatus() == Player.DEAD) continue;
			players.get(i).afterEffect();
			players.get(i).setCurMove(Move.UNDEFINED);
		}
		logs.logCount(round, step);
	}
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public void autoNextStep() {
		if (status == INGAME) {
			int i;
			boolean flag = false;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				if (players.get(i).getBot() == false) {
					flag = true;
					break;
				}
			}
			if (flag) return;
			judge();
		}
		
	}
	
	public void judge() {
		if (status == INGAME) {
			int i;
			// if every player has declared their moves;
			boolean flag = false;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				if (players.get(i).getCurMove().getMoveid() == Move.UNDEFINED){
					if (players.get(i).getBot()) {
						players.get(i).setCurMoveWithAI(this);
					} else {
						flag = true;
					}
				}
			}
			if (flag) return;
			
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				logs.logMove(players.get(i).getName(), players.get(i).getCurMove().getMoveid());
			}
			
			boolean newRound = false;
			int maxPower = 0;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				if (players.get(i).getCurMove().getEnergy() > players.get(i).getEnergy()) {
					players.get(i).die();
					logs.logBaosi(players.get(i).getName());
					newRound = true;
				}
				if (players.get(i).getCurMove().getMoveid() == Move.BI && players.get(i).getBi() == 0) {
					players.get(i).die();
					logs.logBaosi(players.get(i).getName());
					newRound = true;
				}
			}
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				if (players.get(i).getCurMove().getPower() > maxPower) {
					maxPower = players.get(i).getCurMove().getPower();
				}
			}
			for (i=0;i<players.size();i++) {
				if (players.get(i).getStatus() == Player.DEAD) continue;
				if (players.get(i).getCurMove().getDefence() < maxPower) {
					players.get(i).die();
					logs.logFaint(players.get(i).getName());
					newRound = true;
				}
			}
			if (newRound) {
				int aliveCount = 0;
				String winner = "";
				for (i=0;i<players.size();i++) {
					if (players.get(i).getStatus() == Player.ALIVE) {
						aliveCount++;
						winner = players.get(i).getName();
					}
				}
				if (aliveCount < 2) {
					logs.logMsg("Game ends");
					if (aliveCount == 1) {
						logs.logMsg(winner + " wins!");
					} else {
						logs.logMsg("Nobody wins...");
					}
					status = ENDGAME;
				} else {
					newRound();
				}
			} else {
				for (i=0;i<players.size();i++) {
					if (players.get(i).getStatus() == Player.DEAD) continue;
					logs.logAfterEffect(players.get(i).getName(), players.get(i).getCurMove().getMoveid());
				}
				nextStep();
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

	public Logs getLogs() {
		return logs;
	}

	public void setLogs(Logs logs) {
		this.logs = logs;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("step", step);
		doc.append("logs", logs.toDocument());
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
		List<Document> dol = (List<Document>)doc.get("logs");
		logs = new Logs();
		logs.setFromDoc(dol);
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
		dbutil.insert(doc);
	}
	
	public void getGameFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updateCounter() {
		updateDB("round", round);
		updateDB("step", step);
	}
	
	public void updateLogs() {
		updateDB("logs", logs.toDocument());
	}
	
	public void updatePlayerDB(Player p) {
		updateDB("player_" + p.getName(),p.toDocument());
	}
	
	public void updateAllPlayersDB() {
		for (int i=0;i<players.size();i++) {
			updatePlayerDB(players.get(i));
		}
	}
	
	public void updatePlayernamesDB() {
		List<Document> dop = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			Document d = new Document();
			d.append("name", players.get(i).getName());
			dop.add(d);
		}
		updateDB("playernames", dop);
	}
	
}
