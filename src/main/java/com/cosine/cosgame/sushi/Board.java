package com.cosine.cosgame.sushi;

import java.util.List;

public class Board {
	String id;
	String lord;
	int status;
	int round;
	int turn;
	
	List<Player> players;
	List<Card> deck;
	List<Card> menu;
	
	public void startRound() {
		// Step 1: gen deck
		deck = AllRes.genDeck(this);
		
		// Step 2: deal cards
		
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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
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
	public List<Card> getMenu() {
		return menu;
	}
	public void setMenu(List<Card> menu) {
		this.menu = menu;
	}
}
