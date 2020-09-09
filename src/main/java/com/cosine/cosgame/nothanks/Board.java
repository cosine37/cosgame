package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<Player> players;
	List<Card> deck;
	int status;
	int curPlayer;
	int numGoldInDeck;
	AllRes allRes;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		allRes = new AllRes();
	}
	
	public Package genPack() {
		Package pack;
		if (deck.size() != 0) {
			Card c = deck.remove(0);
			pack = new Package();
			pack.setCard(c);
			pack.setMoney(0);
		} else {
			pack = new Package();
		}
		return pack;
	}
	
	public void startGame() {
		status = 1;
		deck = allRes.genDeck(players.size(), numGoldInDeck);
	}
	
	public void endGame() {
		status = 2;
	}
	
	public void sendPack(int x, Package pack) {
		players.get(x).setPack(pack);
		curPlayer = x;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getNumGoldInDeck() {
		return numGoldInDeck;
	}

	public void setNumGoldInDeck(int numGoldInDeck) {
		this.numGoldInDeck = numGoldInDeck;
	}
	
}
