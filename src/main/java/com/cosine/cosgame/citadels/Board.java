package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<Player> players;
	List<Card> deck;
	boolean firstFinished;
	int finishCount;
	int coins;
	int killedRole;
	int stealedRole;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		firstFinished = true;
		finishCount = 8;
		coins = 30;
		killedRole = 0;
		stealedRole = 0;
	}
	
	public int takeCoins(int n) {
		int ans;
		if (coins < n) {
			ans = coins;
			coins = 0;
		} else {
			ans = n;
			coins = coins-n;
		}
		return ans;
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
	public boolean isFirstFinished() {
		return firstFinished;
	}
	public void setFirstFinished(boolean firstFinished) {
		this.firstFinished = firstFinished;
	}
	public int getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}

}
