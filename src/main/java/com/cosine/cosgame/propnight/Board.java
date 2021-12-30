package com.cosine.cosgame.propnight;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Board {
	String id;
	String lord;
	int status;
	int phase;
	
	List<Player> players;
	List<Place> places;
	int humanMark;
	int ghostMark;
	List<Integer> placeSupply;
	List<Card> humanDeck;
	List<Card> ghostDeck;
	
	public Board() {
		
	}
	
	public void endTurn() {
		int i;
		for (i=0;i<places.size();i++) {
			places.get(i).cleanGhostToken();
			places.get(i).cleanHuman();
		}
		for (i=0;i<players.size();i++) {
			players.get(i).endTurn();
		}
	}
	
	public void moveGhostMark(int x) {
		ghostMark = ghostMark + x;
	}
	
	public void moveHumanMark(int x) {
		humanMark = humanMark + x;
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
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public int getHumanMark() {
		return humanMark;
	}
	public void setHumanMark(int humanMark) {
		this.humanMark = humanMark;
	}
	public int getGhostMark() {
		return ghostMark;
	}
	public void setGhostMark(int ghostMark) {
		this.ghostMark = ghostMark;
	}
	public List<Integer> getPlaceSupply() {
		return placeSupply;
	}
	public void setPlaceSupply(List<Integer> placeSupply) {
		this.placeSupply = placeSupply;
	}
	public List<Card> getHumanDeck() {
		return humanDeck;
	}
	public void setHumanDeck(List<Card> humanDeck) {
		this.humanDeck = humanDeck;
	}
	public List<Card> getGhostDeck() {
		return ghostDeck;
	}
	public void setGhostDeck(List<Card> ghostDeck) {
		this.ghostDeck = ghostDeck;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("phase", phase);
		doc.append("humanMark", humanMark);
		doc.append("ghostMark", ghostMark);
		doc.append("placeSupply", placeSupply);
		int i;
		List<Integer> placeLs = new ArrayList<>();
		for (i=0;i<places.size();i++) {
			placeLs.add(places.get(i).getId());
		}
		doc.append("places", placeLs);
		List<Document> lohd = new ArrayList<>();
		for (i=0;i<humanDeck.size();i++) {
			lohd.add(humanDeck.get(i).toDocument());
		}
		doc.append("humanDeck", lohd);
		List<Document> logd = new ArrayList<>();
		for (i=0;i<ghostDeck.size();i++) {
			logd.add(ghostDeck.get(i).toDocument());
		}
		doc.append("ghostDeck", logd);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			doc.append("player-" + name, players.get(i).toDoucment());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		phase = doc.getInteger("phase", -1);
		humanMark = doc.getInteger("humanMark", -1);
		ghostMark = doc.getInteger("ghostMark", -1);
		placeSupply = (List<Integer>) doc.get("placeSupply");
		int i;
		List<Integer> placeLs = (List<Integer>) doc.get("place");
		places = new ArrayList<>();
		for (i=0;i<placeLs.size();i++) {
			places.add(PlaceFactory.makePlace(placeLs.get(i)));
		}
		List<Document> lohd = (List<Document>) doc.get("humanDeck");
		humanDeck = new ArrayList<>();
		for (i=0;i<lohd.size();i++) {
			Card c = CardFactory.makeCard(lohd.get(i));
			humanDeck.add(c);
		}
		List<Document> logd = (List<Document>) doc.get("ghostDeck");
		ghostDeck = new ArrayList<>();
		for (i=0;i<logd.size();i++) {
			Card c = CardFactory.makeCard(logd.get(i));
			ghostDeck.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			Player p = new Player();
			Document d = (Document) doc.get("player-" + name);
			p.setBoard(this);
			p.setFromDoc(d);
		}
	}

}
