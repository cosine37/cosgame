package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Board {
	List<Player> players;
	List<Card> deck;
	List<Card> tomb;
	
	int curPlayerIndex;
	int status;
	int winTarget;
	
	void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	void buildDeck() {
		
	}
	
	void reshuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (tomb.size()>0) {
			int size = tomb.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void startGame() {
		int numPlayers = players.size();
		Random rand = new Random();
		curPlayerIndex  = rand.nextInt(numPlayers);
		buildDeck();
		shuffle();
	}
	
	public List<Card> takeTopCards(int x) {
		List<Card> ans = new ArrayList<>();
		for (int i=0;i<x;i++) {
			if (deck.size() == 0) {
				reshuffle();
			}
			if (deck.size() == 0) {
				break;
			}
			Card c = deck.remove(0);
			ans.add(c);
		}
		return ans;
	}
	
	public Card takeTopCard() {
		List<Card> cards = takeTopCards(1);
		if (cards.size() == 0) {
			return null;
		} else {
			return cards.get(0);
		}
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
	public List<Card> getTomb() {
		return tomb;
	}
	public void setTomb(List<Card> tomb) {
		this.tomb = tomb;
	}
	public int getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(int curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getWinTarget() {
		return winTarget;
	}
	public void setWinTarget(int winTarget) {
		this.winTarget = winTarget;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("curPlayerIndex", curPlayerIndex);
		doc.append("status", status);
		doc.append("winTarget", winTarget);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			String tempName = "player-" + players.get(i).getName();
			doc.append(tempName, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		List<String> decks = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			decks.add(deck.get(i).getName());
		}
		doc.append("deck", decks);
		List<String> tombs = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			tombs.add(deck.get(i).getName());
		}
		doc.append("tomb", tombs);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		curPlayerIndex = doc.getInteger("curPlayerIndex", 0);
		status = doc.getInteger("status", -1);
		winTarget = doc.getInteger("winTarget", 10);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String tempName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(tempName);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			players.add(p);
		}
		List<String> decks = (List<String>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<decks.size();i++) {
			Card c = CardFactory.createCard(decks.get(i));
			deck.add(c);
		}
		List<String> tombs = (List<String>) doc.get("tomb");
		tomb = new ArrayList<>();
		for (i=0;i<decks.size();i++) {
			Card c = CardFactory.createCard(tombs.get(i));
			tomb.add(c);
		}
	}
	
	
}
