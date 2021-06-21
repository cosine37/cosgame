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
	int ruleVariant;
	
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
		AllRes allRes = new AllRes();
		deck = allRes.genDeck();
	}
	
	void reshuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (tomb.size()>0) {
			int size = tomb.size();
			shuffled.add(tomb.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	void dealAll() {
		int i;
		for (i=0;i<players.size();i++) {
			if (i == curPlayerIndex) {
				players.get(i).draw(3);
			} else {
				players.get(i).draw(4);
			}
		}
	}
	
	public void startGame() {
		int numPlayers = players.size();
		status = Consts.INGAME;
		Random rand = new Random();
		curPlayerIndex  = rand.nextInt(numPlayers);
		buildDeck();
		shuffle();
		dealAll();
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
		int i,j;
		
		BoardEntity entity = new BoardEntity();
		
		List<String> playerNames = new ArrayList<>();
		List<String> hand = new ArrayList<>();
		List<List<String>> areaCards = new ArrayList<>();
		List<List<String>> atks = new ArrayList<>();
		List<List<String>> hps = new ArrayList<>();
		String myIndex = "-1";
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			playerNames.add(p.getName());
			
			List<String> singleAreaCards = new ArrayList<>();
			List<String> singleAtks = new ArrayList<>();
			List<String> singleHps = new ArrayList<>();
			for (j=0;j<p.getArea().size();j++) {
				Role r = p.getArea().get(j);
				singleAreaCards.add(r.getCard().getImg());
				singleAtks.add(Integer.toString(r.getAtk()));
				singleHps.add(Integer.toString(r.getHp()));
			}
			areaCards.add(singleAreaCards);
			atks.add(singleAtks);
			hps.add(singleHps);
			
			if (username.contentEquals(players.get(i).getName())) {
				myIndex = Integer.toString(i);
				for (j=0;j<p.getHand().size();j++) {
					hand.add(p.getHand().get(j).getImg());
				}
				
			}
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		entity.setCurPlayerIndex(Integer.toString(curPlayerIndex));
		entity.setPlayers(playerNames);
		entity.setHand(hand);
		entity.setMyIndex(myIndex);
		entity.setAreaCards(areaCards);
		entity.setAtks(atks);
		entity.setHps(hps);
		
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
			decks.add(deck.get(i).getImg());
		}
		doc.append("deck", decks);
		List<String> tombs = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			tombs.add(deck.get(i).getImg());
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
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
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
	
	public void updateBasicDB() {
		updateDB("curPlayerIndex", curPlayerIndex);
		updateDB("status", status);
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
