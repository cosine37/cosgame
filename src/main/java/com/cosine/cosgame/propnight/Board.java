package com.cosine.cosgame.propnight;

import java.util.List;

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

}
