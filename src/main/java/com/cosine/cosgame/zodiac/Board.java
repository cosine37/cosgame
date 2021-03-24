package com.cosine.cosgame.zodiac;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Board {
	List<Player> players;
	List<Zodiac> zodiacs;
	List<Role> roles;
	
	String id;
	int round;
	int phase;
	int status;
	boolean flipped;
	
	public void initializeZodiacs() {
		int i;
		String[] names = {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","苟","猪"};
		zodiacs = new ArrayList<>();
		for (i=0;i<12;i++) {
			Zodiac z = new Zodiac();
			z.setName(names[i]);
			z.setNum(i+1);
			zodiacs.add(z);
		}
	}
	
	public void setZodiacQualities() {
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
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("round", round);
		doc.append("phase", phase);
		doc.append("status", status);
		doc.append("flipped", flipped);
		int i;
		List<String> playerNames = new ArrayList<>();
		List<Document> playerDocs = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add("player-" + players.get(i).getName());
			playerDocs.add(players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		doc.append("players", playerDocs);
		List<Document> zodiacDocs = new ArrayList<>();
		for (i=0;i<zodiacs.size();i++) {
			zodiacDocs.add(zodiacs.get(i).toDocument());
		}
		doc.append("zodiacs", zodiacDocs);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		round = doc.getInteger("round", -1);
		phase = doc.getInteger("phase", -1);
		status = doc.getInteger("status", -1);
		flipped = doc.getBoolean("flipped", false);
		int i;
		List<Document> playerDocs = (List<Document>) doc.get("players");
		players = new ArrayList<>();
		for (i=0;i<playerDocs.size();i++) {
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(playerDocs.get(i));
			p.setIndex(i);
		}
		List<Document> zodiacDocs = (List<Document>) doc.get("zodiacs");
		zodiacs = new ArrayList<>();
		for (i=0;i<zodiacDocs.size();i++) {
			Zodiac z = new Zodiac();
			z.setBoard(this);
			z.setFromDoc(zodiacDocs.get(i));
		}
	}
}
