package com.cosine.cosgame.splendor;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<List<Card>> supply;
	List<List<Card>> deck;
	List<Integer> resources;
	List<Player> players;
	List<Noble> nobles;
	AllRes allRes;
	int curPlayer;
	int status;
	int roundCount;
	int firstPlayer;
	boolean lastRound;
	
	public Board() {
		supply = new ArrayList<>();
		deck = new ArrayList<>();
		resources = new ArrayList<>();
		players = new ArrayList<>();
		nobles = new ArrayList<>();
		allRes = new AllRes();
	}
	
	public void gameSetup() {
		int numPlayers = players.size();
		int resCount = 0;
		if (numPlayers == 2) {
			resCount = 4;
		} else if (numPlayers == 3) {
			resCount = 5;
		} else if (numPlayers == 4) {
			resCount = 7;
		}
		for (int i=0;i<5;i++) {
			resources.add(resCount);
		}
		lastRound = false;
	}
	
	public Card drawOne(int x) {
		if (x>=0 && x<3) {
			if (deck.get(x).size() > 0) {
				Card c = deck.get(x).remove(0);
				return c;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public Card takeFromSupply(int x, int y) {
		if (x>=0 && x<3) {
			if (y>=0 && y<4) {
				Card c = supply.get(x).get(y);
				if (deck.get(x).size() > 0) {
					Card newCard = deck.get(x).remove(0);
					supply.get(x).set(y, newCard);
				} else {
					supply.get(x).set(y, null);
				}
				return c;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	public List<List<Card>> getSupply() {
		return supply;
	}
	public void setSupply(List<List<Card>> supply) {
		this.supply = supply;
	}
	public List<List<Card>> getDeck() {
		return deck;
	}
	public void setDeck(List<List<Card>> deck) {
		this.deck = deck;
	}
	public List<Integer> getResources() {
		return resources;
	}
	public void setResources(List<Integer> resources) {
		this.resources = resources;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Noble> getNobles() {
		return nobles;
	}
	public void setNobles(List<Noble> nobles) {
		this.nobles = nobles;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public boolean isLastRound() {
		return lastRound;
	}
	public void setLastRound(boolean lastRound) {
		this.lastRound = lastRound;
	}
	
}
