package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name;
	int index;
	int phase;
	int coins;
	boolean stillIn;
	boolean alive;
	boolean confirmed;
	
	List<Card> hand;
	List<Card> discard;
	
	West west;
	
	public Player() {
		
	}
	
	public void newGame() {
		alive = true;
		newRound();
	}
	
	public void newRound() {
		hand = new ArrayList<>();
		discard = new ArrayList<>();
		if (alive) {
			stillIn = true;
		}
	}
	
	public void draw() {
		Card c = west.removeTop();
		if (c != null) {
			hand.add(c);
		}
	}
	
	public void discard(int x) {
		if (hand.size()>x) {
			Card c = hand.remove(x);
			discard.add(c);
		}
	}
	
	public void bid(int x) {
		coins = coins-x;
		west.addPool(x);
	}
	
	public int getHandNum() {
		return hand.get(0).getNum();
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
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public boolean isStillIn() {
		return stillIn;
	}
	public void setStillIn(boolean stillIn) {
		this.stillIn = stillIn;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public West getWest() {
		return west;
	}
	public void setWest(West west) {
		this.west = west;
	}
	
}
