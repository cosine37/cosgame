package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.flip7.entity.CardEntity;
import com.cosine.cosgame.oink.flip7.entity.Flip7Entity;
import com.cosine.cosgame.oink.flip7.entity.Flip7PlayerEntity;
import com.cosine.cosgame.oink.grove.GrovePlayer;
import com.cosine.cosgame.util.MongoDBUtil;

public class Flip7 {
	List<Card> deck;
	List<Card> discard;
	List<Flip7Player> players;
	int round;
	int firstPlayer;
	int curPlayer;
	
	MongoDBUtil dbutil;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		List<Document> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).toDocument());
		}
		doc.append("deck",deckDocList);
		List<Document> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).toDocument());
		}
		doc.append("discard",discardDocList);
		for (i=0;i<players.size();i++){
			players.get(i).setIndex(i);
			String n = "player-" + players.get(i).getName();
			doc.append(n, players.get(i).toDocument());
		}
		doc.append("round",round);
		doc.append("firstPlayer",firstPlayer);
		doc.append("curPlayer", curPlayer);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		List<Document> deckDocList = (List<Document>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(deckDocList.get(i));
			deck.add(e);
		}
		List<Document> discardDocList = (List<Document>)doc.get("discard");
		discard = new ArrayList<>();
		for (i=0;i<discardDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(discardDocList.get(i));
			discard.add(e);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++){
			String n = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(n);
			Flip7Player p = new Flip7Player();
			p.setFlip7(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
		round = doc.getInteger("round",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		curPlayer = doc.getInteger("curPlayer", 0);
	}
	
	public Flip7Entity toFlip7Entity(String username){
		int i,j;
		Flip7Entity entity = new Flip7Entity();
		List<CardEntity> listOfDeck = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			listOfDeck.add(deck.get(i).toCardEntity());
		}
		entity.setDeck(listOfDeck);
		List<CardEntity> listOfDiscard = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			listOfDiscard.add(discard.get(i).toCardEntity());
		}
		entity.setDiscard(listOfDiscard);
		List<Flip7PlayerEntity> listOfPlayers = new ArrayList<>();
		for (i=0;i<players.size();i++){
			listOfPlayers.add(players.get(i).toFlip7PlayerEntity(username));
			if (players.get(i).getName().contentEquals(username)){
				Flip7Player p = players.get(i);
			}
		}
		entity.setPlayers(listOfPlayers);
		entity.setRound(round);
		entity.setFirstPlayer(firstPlayer);
		entity.setCurPlayer(curPlayer);
		return entity;
	}
	
	public Flip7(Board board) {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		
		this.board = board;
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void shuffle() {
		List<Card> newDeck = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int x = rand.nextInt(deck.size());
			Card c = deck.remove(x);
			newDeck.add(c);
		}
		deck = newDeck;
	}
	
	public Card deal() {
		if (deck.size() == 0) {
			deck = discard;
			discard = new ArrayList<>();
			shuffle();
		}
		Card c = deck.remove(0);
		return c;
	}
	
	public void startGame() {
		round = 0;
		
		startRound();
	}
	
	public void startRound() {
		round++;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).startRound();
		}
	}
	
	public boolean roundEnd() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isActive()) {
				return false;
			}
		}
		return true;
	}
	
	public void endRound() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).endRound();
		}
	}
	
	public Flip7Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	// Begin actual operations
	public void startGameUDB() {
		// Step 1: create players
		int i;
		List<String> playerNames = board.getPlayerNames();
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Flip7Player p = new Flip7Player(playerNames.get(i));
			p.setFlip7(this);
			players.add(p);
		}
		
		startGame();
		
		updatePlayers();
		updateBasicDB();
	}
	
	// End actual operations
	
	public void updatePlayer(int index) {
		Flip7Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		Flip7Player p = getPlayerByName(name);
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
		//updateDB("logs", logger.getLogs());
		int i;
		List<Document> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).toDocument());
		}
		updateDB("deck",deckDocList);
		List<Document> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).toDocument());
		}
		updateDB("discard",discardDocList);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}

	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public List<Flip7Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Flip7Player> players) {
		this.players = players;
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
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	
}
