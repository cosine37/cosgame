package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.util.MongoDBUtil;

public class PopeGame {
	int round;
	int firstPlayer;
	int curPlayer;
	List<PopePlayer> players;
	List<Card> deck;
	Logger logger;
	
	Board board;
	
	MongoDBUtil dbutil;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("round",round);
		doc.append("firstPlayer",firstPlayer);
		doc.append("curPlayer",curPlayer);
		for (i=0;i<players.size();i++){
			players.get(i).setIndex(i);
			String n = "player-" + players.get(i).getName();
			doc.append(n, players.get(i).toDocument());
		}
		List<Integer> deckList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckList.add(deck.get(i).getNum());
		}
		doc.append("deck",deckList);
		doc.append("logs",logger.getLogs());
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		round = doc.getInteger("round",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		curPlayer = doc.getInteger("curPlayer",0);
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++){
			String n = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(n);
			PopePlayer p = new PopePlayer();
			p.setGame(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
		List<Integer> deckList = (List<Integer>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckList.size();i++){
			Card e = CardFactory.makeCard(deckList.get(i));
			deck.add(e);
		}
		List<String> logs = (List<String>) doc.get("logs");
		if (logs == null) logger = new Logger(); else logger = new Logger(logs);
	}
	
	public PopeGame() {
		round = 0;
		players = new ArrayList<>();
		deck = new ArrayList<>();
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public Card removeFromDeck() {
		if (deck.size()>0) {
			Card c = deck.remove(0);
			return c;
		} else {
			return null;
		}
	}
	
	public PopePlayer getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public void updatePlayer(int index) {
		PopePlayer p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		PopePlayer p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void updateBasicDB() {
		updateDB("round", round);
		updateDB("status", board.getStatus());
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("logs", logger.getLogs());
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}
	
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<PopePlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<PopePlayer> players) {
		this.players = players;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
}
