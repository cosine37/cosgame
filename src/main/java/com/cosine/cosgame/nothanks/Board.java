package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("status", status);
		doc.append("curPlayer", curPlayer);
		doc.append("numGoldInDeck", numGoldInDeck);
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
		}
		doc.append("playerNames", playerNames);
		List<Integer> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getNum());
		}
		doc.append("deck", lod);
		for (i=0;i<players.size();i++) {
			String key = "player-" + players.get(i).getName();
			Document dop = players.get(i).toDocument();
			doc.append(key, dop);
		}
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		status = doc.getInteger("status", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		numGoldInDeck = doc.getInteger("numGoldInDeck", -1);
		
		int i;
		
		players = new ArrayList<>();
		List<String> playerNames = (List<String>) doc.get("playerNames");
		for (i=0;i<playerNames.size();i++) {
			String key = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(key);
			Player p = new Player();
			p.setFromDoc(dop);
		}
		
		deck = new ArrayList<>();
		List<Integer> lod = (List<Integer>) doc.get("deck");
		for (i=0;i<lod.size();i++) {
			Card c = CardFactory.createCard(lod.get(i));
			deck.add(c);
		}
	}
	
}
