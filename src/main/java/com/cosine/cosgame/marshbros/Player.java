package com.cosine.cosgame.marshbros;

import java.util.List;

public class Player {
	String name;
	int phase;
	int resource;
	
	Board board;
	
	List<Card> hand;
	List<Card> area;
	
	public Player() {
		
	}
	
	public void draw() {
		Card c = board.takeTopCard();
		hand.add(c);
	}
	
	public void draw(int x) {
		List<Card> cards = board.takeTopCards(x);
		for (int i=0;i<cards.size();i++) {
			Card c = cards.get(i);
			hand.add(c);
		}
		
	}
	
	public void moveToTomb(int x) {
		if (x>=0 && x<area.size()) {
			Card c = area.remove(x);
			board.getTomb().add(c);
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getResource() {
		return resource;
	}
	public void setResource(int resource) {
		this.resource = resource;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getArea() {
		return area;
	}
	public void setArea(List<Card> area) {
		this.area = area;
	}

	
}
