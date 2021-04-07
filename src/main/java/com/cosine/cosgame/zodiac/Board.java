package com.cosine.cosgame.zodiac;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Zodiac> zodiacs;
	List<Role> roles;
	
	String id;
	String lord;
	int round;
	int phase;
	int status;
	boolean flipped;
	
	MongoDBUtil dbutil;
	
	public Board() {
		players = new ArrayList<>();
		zodiacs = new ArrayList<>();
		roles = new ArrayList<>();
		
		String dbname = "zodiac";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public void setZodiacQualities() {
		zodiacs = AllRes.genZodiacs();
		int i;
		List<Boolean> bs = new ArrayList<>();
		for (i=0;i<6;i++) {
			bs.add(true);
			bs.add(false);
		}
		i = 0;
		while (bs.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(bs.size());
			Boolean f = bs.remove(x);
			zodiacs.get(i).setReal(f);
			i++;
		}
	}
	
	public void randomizeZodiacs() {
		int i;
		List<Zodiac> tempZ = new ArrayList<>();
		for (i=0;i<zodiacs.size();i++) {
			tempZ.add(zodiacs.get(i));
		}
		zodiacs = new ArrayList<>();
		while (tempZ.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(tempZ.size());
			Zodiac z = tempZ.remove(x);
			zodiacs.add(z);
		}
	}
	
	public void distributeRoles() {
		roles = AllRes.genRoles(players.size());
		int i;
		List<Role> tempRoles = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			tempRoles.add(roles.get(i));
		}
		for (i=0;i<players.size();i++) {
			Random rand = new Random();
			int x = rand.nextInt(tempRoles.size());
			Role r = tempRoles.remove(x);
			players.get(i).setRole(r);
		}
	}
	
	public void keepAndReveal(int roundNum) {
		int i,j;
		List<Zodiac> tempZ = new ArrayList<>();
		for (i=0;i<4;i++) {
			int x = roundNum*4+i;
			tempZ.add(zodiacs.get(x));
		}
		for (i=0;i<4;i++) {
			for (j=i+1;j<4;j++) {
				Zodiac zi = tempZ.get(i);
				Zodiac zj = tempZ.get(j);
				boolean exchange = false;
				if (zi.getVotes() < zj.getVotes()) {
					exchange = true;
				} else if (zi.getVotes() == zj.getVotes()) {
					if (zj.getNum() < zi.getNum()) {
						exchange = true;
					}
				}
				if (exchange) {
					tempZ.set(i, zj);
					tempZ.set(j, zi);
				}
			}
		}
		Zodiac z0 = tempZ.get(0);
		Zodiac z1 = tempZ.get(1);
		z0.setKeep(true);
		z1.setKeep(true);
		if (z0.getVotes() > z1.getVotes()) {
			z1.setReveal(true);
		} else if (z0.getVotes() < z1.getVotes()) {
			z0.setReveal(true);
		} else {
			if (z1.getNum() < z0.getNum()) {
				z1.setReveal(true);
			} else {
				z0.setReveal(true);
			}
		}
	}
	
	public void newRound() {
		flipped = false;
		phase = Consts.INSPECT;
		round++;
		for (int i=0;i<players.size();i++) {
			players.get(i).newRound();
		}
	}

	public void startGame() {
		if (players.size()>=6 && players.size() <=8) {
			setZodiacQualities();
			randomizeZodiacs();
			distributeRoles();
			status = Consts.INGAME;
			newRound();
		}
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Zodiac> getZodiacs() {
		return zodiacs;
	}
	public void setZodiacs(List<Zodiac> zodiacs) {
		this.zodiacs = zodiacs;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public boolean isFlipped() {
		return flipped;
	}
	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
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
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setPhase(Integer.toString(phase));
		entity.setStatus(Integer.toString(status));
		entity.setRound(Integer.toString(round));
		
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
		}
		entity.setPlayers(playerNames);
		List<String> zodiacNames = new ArrayList<>();
		List<String> zodiacImages = new ArrayList<>();
		if (status == Consts.INGAME) {
			for (i=0;i<round*4;i++) {
				zodiacNames.add(zodiacs.get(i).getName());
				zodiacImages.add(zodiacs.get(i).getImg());
			}
		}
		
		entity.setZodiacs(zodiacNames);
		entity.setZodiacImages(zodiacImages);
		
		return entity;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("round", round);
		doc.append("phase", phase);
		doc.append("status", status);
		doc.append("flipped", flipped);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			doc.append("player-" + players.get(i).getName(), players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		List<Document> zodiacDocs = new ArrayList<>();
		for (i=0;i<zodiacs.size();i++) {
			zodiacDocs.add(zodiacs.get(i).toDocument());
		}
		doc.append("zodiacs", zodiacDocs);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		round = doc.getInteger("round", -1);
		phase = doc.getInteger("phase", -1);
		status = doc.getInteger("status", -1);
		flipped = doc.getBoolean("flipped", false);
		int i;
		
		List<String> playerNames = (List<String>) doc.get("playerNames");
		
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Player p = new Player();
			Document playerDoc = (Document) doc.get("player-" + playerNames.get(i));
			p.setBoard(this);
			p.setFromDoc(playerDoc);
			p.setIndex(i);
			players.add(p);
		}
		List<Document> zodiacDocs = (List<Document>) doc.get("zodiacs");
		zodiacs = new ArrayList<>();
		for (i=0;i<zodiacDocs.size();i++) {
			Zodiac z = new Zodiac();
			z.setBoard(this);
			z.setFromDoc(zodiacDocs.get(i));
			zodiacs.add(z);
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
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void updateZodiacs() {
		List<Document> zodiacDocs = new ArrayList<>();
		for (int i=0;i<zodiacs.size();i++) {
			zodiacDocs.add(zodiacs.get(i).toDocument());
		}
		dbutil.update("id", id, "zodiacs", zodiacDocs);
	}
	
	public void updateBasicDB() {
		dbutil.update("id", id, "status", status);
		dbutil.update("id", id, "round", round);
		dbutil.update("id", id, "phase", phase);
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		p.setDisplayName(name);
		players.add(p);
	}
	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player();
		bot.setName(botName);
		bot.setDisplayName(botName);
		bot.setBot(true);
		players.add(bot);
		addPlayerToDB(botName);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
}
