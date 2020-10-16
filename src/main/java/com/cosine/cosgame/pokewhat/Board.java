package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<List<Card>> playedCards;
	List<Card> ancient;
	List<Card> deck;
	int status;
	int round;
	int turn;
	int curPlayer;
	String id;
	AllRes allRes;
	MongoDBUtil dbutil;
	
	public Board(){
		players = new ArrayList<>();
		playedCards = new ArrayList<>();
		allRes = new AllRes();
		
		String dbname = "pokewhat";
		String col = "board";
	}
	
	public void genDeck() {
		
	}
	
	public void removeCards(int x) {
		if (x == 2) {
			
		} else if (x == 3) {
			
		}
	}
	
	public void genAncient() {
		for (int i=0;i<PokewhatConsts.NUMANCIENT;i++) {
			Card c = deck.remove(0);
			ancient.add(c);
		}
	}
	
	public void startGame() {
		int i;
		for (i=0;i<9;i++) {
			List<Card> ls = new ArrayList<>();
			playedCards.add(ls);
		}
		genDeck();
		removeCards(players.size());
		genAncient();
	}
	
	public boolean isRoundEnd() {
		boolean ans = false;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getHp() == 0) {
				ans = true;
			}
		}
		return ans;
	}
	
	public void nextRound() {
		round++;
	}
	
	public void addToPlayedCards(Card c) {
		int index = c.getNum();
		playedCards.get(index).add(c);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Card>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<Card>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
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
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id",id);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("turn", turn);
		doc.append("curPlayer", curPlayer);
		int i;
		List<String> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getImg());
		}
		doc.append("deck", lod);
		List<String> loa = new ArrayList<>();
		for (i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		doc.append("ancient", loa);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(playerName);
			doc.append(playerName, players.get(i).toDocument());
		}
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		turn = doc.getInteger("turn", 0);
		curPlayer = doc.getInteger("curPlayer", 0);
		int i;
		List<String> lod = (List<String>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<lod.size();i++) {
			deck.add(CardFactory.createCard(lod.get(i)));
		}
		List<String> loa = (List<String>) doc.get("ancient");
		ancient = new ArrayList<>();
		for (i=0;i<loa.size();i++) {
			ancient.add(CardFactory.createCard(loa.get(i)));
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	
}
