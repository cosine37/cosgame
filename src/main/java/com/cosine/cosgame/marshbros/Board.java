package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Card> deck;
	List<Card> tomb;
	
	int curPlayerIndex;
	int status;
	int winTarget;
	
	String id;
	String lord;
		
	MongoDBUtil dbutil;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		tomb = new ArrayList<>();
		
		String dbname = "marshbros";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	void buildDeck() {
		
	}
	
	void reshuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (tomb.size()>0) {
			int size = tomb.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void startGame() {
		int numPlayers = players.size();
		Random rand = new Random();
		curPlayerIndex  = rand.nextInt(numPlayers);
		buildDeck();
		shuffle();
	}
	
	public List<Card> takeTopCards(int x) {
		List<Card> ans = new ArrayList<>();
		for (int i=0;i<x;i++) {
			if (deck.size() == 0) {
				reshuffle();
			}
			if (deck.size() == 0) {
				break;
			}
			Card c = deck.remove(0);
			ans.add(c);
		}
		return ans;
	}
	
	public Card takeTopCard() {
		List<Card> cards = takeTopCards(1);
		if (cards.size() == 0) {
			return null;
		} else {
			return cards.get(0);
		}
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
	public List<Card> getTomb() {
		return tomb;
	}
	public void setTomb(List<Card> tomb) {
		this.tomb = tomb;
	}
	public int getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(int curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getWinTarget() {
		return winTarget;
	}
	public void setWinTarget(int winTarget) {
		this.winTarget = winTarget;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		players.add(p);
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		
		List<String> playerNames = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		entity.setPlayers(playerNames);
		
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("curPlayerIndex", curPlayerIndex);
		doc.append("status", status);
		doc.append("winTarget", winTarget);
		doc.append("lord", lord);
		doc.append("id", id);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			String tempName = "player-" + players.get(i).getName();
			doc.append(tempName, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		List<String> decks = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			decks.add(deck.get(i).getName());
		}
		doc.append("deck", decks);
		List<String> tombs = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			tombs.add(deck.get(i).getName());
		}
		doc.append("tomb", tombs);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		curPlayerIndex = doc.getInteger("curPlayerIndex", 0);
		status = doc.getInteger("status", -1);
		winTarget = doc.getInteger("winTarget", 10);
		lord = doc.getString("lord");
		id = doc.getString("id");
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String tempName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(tempName);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			players.add(p);
		}
		List<String> decks = (List<String>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<decks.size();i++) {
			Card c = CardFactory.createCard(decks.get(i));
			deck.add(c);
		}
		List<String> tombs = (List<String>) doc.get("tomb");
		tomb = new ArrayList<>();
		for (i=0;i<decks.size();i++) {
			Card c = CardFactory.createCard(tombs.get(i));
			tomb.add(c);
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
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
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
