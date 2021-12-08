package com.cosine.cosgame.threechaodoms;

import java.util.List;

public class Player {
	String name;
	int index;
	
	ID id;
	List<Card> hand;
	List<Card> play;
	List<Card> jail;
	
	Board board;
	
	public void exile(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			board.addToExile(c);
		}
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			Card c = board.popTopDeck();
			if (c == null) return;
			hand.add(c);
		}
	}
	
	public void draw() {
		draw(1);
	}
	
	public void takeFromTavern(int x) {
		Card c = board.takeFromTavern(x);
		if (c != null) {
			hand.add(c);
		}
	}
	
	public void putInJail(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			jail.add(c);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public List<Card> getJail() {
		return jail;
	}
	public void setJail(List<Card> jail) {
		this.jail = jail;
	}
	
}
