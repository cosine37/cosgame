package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

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
}
