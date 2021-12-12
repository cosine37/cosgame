package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Board {
	String id;
	String lord;
	int phase;
	int status;
	int curPlayer;
	int weiPos;
	int hanPos;
	List<Player> players;
	List<Card> deck;
	List<Card> tavern;
	List<Card> exile;
	List<Card> tomb;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		tavern = new ArrayList<>();
		exile = new ArrayList<>();
		tomb = new ArrayList<>();
	}
	
	public Card takeFromTavern(int x) {
		if (x>=0 && x<tavern.size()) {
			Card c = tavern.get(x);
			if (c.isBlankSpace()) {
				return null;
			} else {
				tavern.set(x, new BlankSpaceCard());
				return c;
			}
		} else {
			return null;
		}
	}
	
	public Card popTopDeck() {
		if (deck.size() == 0) {
			return null;
		} else {
			Card c = deck.remove(0);
			return c;
		}
	}
	
	public void refillTavern() {
		int i;
		for (i=0;i<tavern.size();i++) {
			if (tavern.get(i).isBlankSpace()) {
				Card c = popTopDeck();
				if (c == null) {
					return;
				} else {
					tavern.set(i, c);
				}
			}
		}
	}
	
	public void moveWei(int x) {
		weiPos = weiPos+x;
	}
	public void moveHan(int x) {
		hanPos = hanPos+x;
	}
	public void addToExile(Card c) {
		exile.add(c);
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
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getWeiPos() {
		return weiPos;
	}
	public void setWeiPos(int weiPos) {
		this.weiPos = weiPos;
	}
	public int getHanPos() {
		return hanPos;
	}
	public void setHanPos(int hanPos) {
		this.hanPos = hanPos;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getTavern() {
		return tavern;
	}
	public void setTavern(List<Card> tavern) {
		this.tavern = tavern;
	}
	public List<Card> getExile() {
		return exile;
	}
	public void setExile(List<Card> exile) {
		this.exile = exile;
	}
	public List<Card> getTomb() {
		return tomb;
	}
	public void setTomb(List<Card> tomb) {
		this.tomb = tomb;
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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("phase", phase);
		doc.append("curPlayer", curPlayer);
		doc.append("weiPos", weiPos);
		doc.append("hanPos", hanPos);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		List<Document> dod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dod.add(deck.get(i).toDocument());
		}
		doc.append("deck", dod);
		List<Document> dot = new ArrayList<>();
		for (i=0;i<tavern.size();i++) {
			dot.add(tavern.get(i).toDocument());
		}
		doc.append("tavern", dot);
		List<Document> doe = new ArrayList<>();
		for (i=0;i<exile.size();i++) {
			doe.add(exile.get(i).toDocument());
		}
		doc.append("exile", doe);
		List<Document> doo = new ArrayList<>();
		for (i=0;i<tomb.size();i++) {
			doo.add(tomb.get(i).toDocument());
		}
		doc.append("tomb", doo);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		phase = doc.getInteger("phase", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		weiPos = doc.getInteger("weiPos", -1);
		hanPos = doc.getInteger("hanPos", -1);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			name = "player-" + name;
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setFromDoc(dop);
			p.setIndex(i);
		}
		List<Document> dod = (List<Document>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<dod.size();i++) {
			Card c = CardFactory.makeCard(dod.get(i));
			c.setWhere(Consts.DECK);
			deck.add(c);
		}
		List<Document> dot = (List<Document>) doc.get("tavern");
		tavern = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Card c = CardFactory.makeCard(dot.get(i));
			c.setWhere(Consts.TAVERN);
			tavern.add(c);
		}
		List<Document> doe = (List<Document>) doc.get("exile");
		exile = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Card c = CardFactory.makeCard(doe.get(i));
			c.setWhere(Consts.EXILE);
			exile.add(c);
		}
		List<Document> doo = (List<Document>) doc.get("tomb");
		tomb = new ArrayList<>();
		for (i=0;i<doo.size();i++) {
			Card c = CardFactory.makeCard(doo.get(i));
			c.setWhere(Consts.TOMB);
			tomb.add(c);
		}
	}
	
}
