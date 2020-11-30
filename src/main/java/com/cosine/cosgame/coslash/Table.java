package com.cosine.cosgame.coslash;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Table {
	List<Player> players;
	List<Card> deck;
	List<Card> toResolve;
	
	int status;
	int round;
	int curPlayerTurn;
	int curPlayer;
	public static final int CHOOSEGENERAL = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	public Table() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
	}
	
	public boolean checkGameEnd() {
		boolean ans = false;
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
	public int getCurPlayerTurn() {
		return curPlayerTurn;
	}
	public void setCurPlayerTurn(int curPlayerTurn) {
		this.curPlayerTurn = curPlayerTurn;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("status", status);
		doc.append("round", round);
		doc.append("curPlayerTurn", curPlayerTurn);
		doc.append("curPlayer", curPlayer);
		List<Document> dod = new ArrayList<>();
		for (int i=0; i<deck.size();i++) {
			Document dc = deck.get(i).toDocument();
			dod.add(dc);
		}
		doc.append("deck", dod);
		List<Document> dor = new ArrayList<>();
		for (int i=0;i<toResolve.size();i++) {
			Document dc = toResolve.get(i).toDocument();
			dor.add(dc);
		}
		doc.append("toResolve", dor);
		List<Document> dop = new ArrayList<>();
		for (int i=0; i<players.size();i++) {
			Document dp = players.get(i).toDocument();
			dop.add(dp);
		}
		doc.append("players", dop);
		return doc;
	}
	
	public void setTableFromDoc(Document doc) {
		status = doc.getInteger("status");
		round = doc.getInteger("round", 0);
		curPlayerTurn = doc.getInteger("curPlayerTurn", 0);
		curPlayer = doc.getInteger("curPlayer", 0);
		deck = new ArrayList<>();
		List<Document> dod = (List<Document>)doc.get("deck");
		for (int i=0; i<dod.size();i++) {
			Card c = CardFactory.createCard(dod.get(i));
			deck.add(c);
		}
		List<Document> dor = (List<Document>)doc.get("toResolve");
		for (int i=0;i<dor.size();i++) {
			Card c = CardFactory.createCard(dor.get(i));
			toResolve.add(c);
		}
		players = new ArrayList<>();
		List<Document> dop = (List<Document>)doc.get("players");
		for (int i=0;i<dop.size();i++) {
			Player p = new Player();
			p.setPlayerFromDoc(dop.get(i));
			players.add(p);
		}
	}
	
}
