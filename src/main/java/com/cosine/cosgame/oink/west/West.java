package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.util.MongoDBUtil;

public class West {
	List<Player> players;
	int status;
	int round;
	int pool;
	int winner;
	int firstPlayer;
	int curPlayer;
	
	List<Card> deck;
	List<Card> assist;
	
	Board board;
	
	MongoDBUtil dbutil;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		for (i=0;i<players.size();i++){
			players.get(i).setIndex(i);
			String n = "player-" + players.get(i).getName();
			doc.append(n, players.get(i).toDocument());
		}
		doc.append("status",status);
		doc.append("round",round);
		doc.append("pool",pool);
		doc.append("winner",winner);
		doc.append("firstPlayer",firstPlayer);
		doc.append("curPlayer",curPlayer);
		List<Document> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).toDocument());
		}
		doc.append("deck",deckDocList);
		List<Document> assistDocList = new ArrayList<>();
		for (i=0;i<assist.size();i++){
			assistDocList.add(assist.get(i).toDocument());
		}
		doc.append("assist",assistDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++){
			String n = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setWest(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
		status = doc.getInteger("status",0);
		round = doc.getInteger("round",0);
		pool = doc.getInteger("pool",0);
		winner = doc.getInteger("winner",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		curPlayer = doc.getInteger("curPlayer",0);
		List<Document> deckDocList = (List<Document>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(deckDocList.get(i));
			deck.add(e);
		}
		List<Document> assistDocList = (List<Document>)doc.get("assist");
		assist = new ArrayList<>();
		for (i=0;i<assistDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(assistDocList.get(i));
			assist.add(e);
		}
	}
	
	public West(Board board) {
		this.board = board;
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void newRound() {
		// Step 1: update deck, assist and pool
		round++;
		deck = AllRes.genShuffledDeck();
		assist = new ArrayList<>();
		updateAssist();
		// TODO: may need to update this
		if (round == 7) {
			pool = 2;
		} else {
			pool = 1;
		}
		
		// Step 2: update all players
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).newRound();
		}
	}
	
	public Card removeTop() {
		if (deck.size() == 0) {
			return null;
		} else {
			Card c = deck.remove(0);
			return c;
		}
	}
	
	public void addPool(int x) {
		pool = pool+x;
	}
	
	public void updateAssist() {
		Card c = removeTop();
		if (c != null) {
			assist.add(c);
		}
	}
	
	public Card getCurAssist() {
		int x = assist.size()-1;
		if (x>=0) {
			return assist.get(x);
		} else {
			return null;
		}
	}
	
	public void calcWinner() {
		winner = -1;
		int minIndex = -1;
		int min = 99;
		int max = 0;
		int i;
		
		// Step 1: Calc min player
		for (i=0;i<players.size();i++) {
			if (players.get(i).getHandNum() < min) {
				min = players.get(i).getHandNum();
				minIndex = i;
			}
		}
		
		// Step 2: Calc max and winner
		int t,x;
		for (i=0;i<players.size();i++) {
			t = (firstPlayer+i) % players.size(); // closer to first player wins
			if (players.get(t).isStillIn()) {
				x = players.get(t).getHandNum();
				if (t == minIndex) {
					x = x+getCurAssist().getNum();
				}
				if (x>max) {
					max = x;
					winner = t;
				}
			}
		}
	}
	
	public boolean oneStillIn() {
		int i;
		int x = 0;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				x++;
			}
		}
		return x == 1;
	}
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
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
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		updateDB("deck", dok);
		List<Document> doa = new ArrayList<>();
		for (i=0;i<assist.size();i++) {
			doa.add(assist.get(i).toDocument());
		}
		updateDB("assist", doa);
		
		updateDB("round", round);
		updateDB("status", board.getStatus());
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("winner", winner);
		updateDB("pool", pool);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}
	
	public boolean hasPlayer(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return true;
			}
		}
		return false;
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
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
	public int getPool() {
		return pool;
	}
	public void setPool(int pool) {
		this.pool = pool;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
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
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getAssist() {
		return assist;
	}
	public void setAssist(List<Card> assist) {
		this.assist = assist;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
}
