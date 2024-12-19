package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.pope.entity.CardEntity;
import com.cosine.cosgame.oink.pope.entity.PopeEntity;
import com.cosine.cosgame.oink.pope.entity.PopePlayerEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class PopeGame {
	int round;
	int firstPlayer;
	int curPlayer;
	String endRoundMsg;
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
		doc.append("endRoundMsg", endRoundMsg);
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
		endRoundMsg = doc.getString("endRoundMsg");
	}
	
	public PopeEntity toPopeEntity(String username){
		int i,j;
		PopeEntity entity = new PopeEntity();
		entity.setStatus(board.getStatus());
		entity.setRound(round);
		entity.setCurPlayer(curPlayer);
		entity.setFirstPlayer(firstPlayer);
		entity.setDeckSize(deck.size());
		List<PopePlayerEntity> listOfPlayers = new ArrayList<>();
		for (i=0;i<players.size();i++){
			listOfPlayers.add(players.get(i).toPopePlayerEntity());
			if (players.get(i).getName().contentEquals(username)){
				PopePlayer p = players.get(i);
				entity.setPhase(p.getPhase());
				List<CardEntity> listOfHand = new ArrayList<>();
				for (j=0;j<p.getHand().size();j++){
					listOfHand.add(p.getHand().get(j).toCardEntity());
				}
				entity.setHand(listOfHand);
				entity.setMyIndex(i);
			}
		}
		entity.setPlayers(listOfPlayers);
		entity.setLogs(logger.getLogs());
		entity.setEndRoundMsg(endRoundMsg);
		return entity;
	}

	
	public PopeGame(Board board) {
		this.board = board;
		round = 0;
		players = new ArrayList<>();
		deck = new ArrayList<>();
		logger = new Logger();
		endRoundMsg = "";
		
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
	
	public void startRound() {
		int i;
		// Step 1: status, round # and initialize deck
		board.setStatus(Consts.INGAME);
		round++;
		deck = AllRes.allBaseCards();
		endRoundMsg = "";
		
		// Step 2: initialize players
		for (i=0;i<players.size();i++) {
			players.get(i).startRound();
		}
		
		// Step 3: curPlayer handle
		curPlayer = firstPlayer;
		players.get(curPlayer).startTurn();
	}
	
	public boolean roundEnd() {
		if (deck.size() == 0) return true;
		int numActive = 0;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isActive()) numActive++;
		}
		if (numActive == 1) return true;
		return false;
	}
	
	public void endRound() {
		// Step 1: set status
		board.setStatus(Consts.ROUNDEND);
		
		// Step 2: reveal hand and find the player;
		int i;
		int max = -1;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isActive()) {
				int x = players.get(i).getHand().get(0).getNum();
				if (max<x) {
					max = x;
				}
			}
			
		}
		List<Integer> winnerIds = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			if (players.get(i).isActive()) {
				int x = players.get(i).getHand().get(0).getNum();
				if (max == x) {
					winnerIds.add(i);
					players.get(i).addKey();
				}
			}
		}
		
		// Step 3: set end game msg
		endRoundMsg = "本轮获胜者是";
		for (i=0;i<winnerIds.size();i++) {
			if (i == 0) {
				endRoundMsg = endRoundMsg + " " + players.get(winnerIds.get(i)).getName() + " ";
			} else {
				endRoundMsg = endRoundMsg + "和 " + players.get(winnerIds.get(i)).getName() + " ";
			}
		}
		
		// Step 4: decide first player next round
		Random rand = new Random();
		int y = rand.nextInt(winnerIds.size());
		firstPlayer = winnerIds.get(y);
		
		// Step 5: clear confirmed for players
		for (i=0;i<players.size();i++) {
			players.get(i).setConfirmed(false);
		}
	}
	
	public boolean gameEnd() {
		return false;
	}
	
	public void endGame() {
		
	}
	
	// actual operations
	public void startGameUDB() {
		// Step 1: create players
		int i;
		List<String> playerNames = board.getPlayerNames();
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			PopePlayer p = new PopePlayer(playerNames.get(i));
			p.setGame(this);
			players.add(p);
		}
		
		
		// Step 2: set round and curPlayer
		round = 0;
		curPlayer = board.getFirstPlayer();
		firstPlayer = board.getFirstPlayer();
		startRound();
		
		updatePlayers();
		updateBasicDB();
	}
	
	public void playerPlayUDB(String username, int cardIndex) {
		PopePlayer p = getPlayerByName(username);
		if (p != null) {
			if (cardIndex>=0 && cardIndex<p.getHand().size()) {
				// Step 1: empty play area
				for (int i=0;i<players.size();i++) {
					players.get(i).setPlay(null);
				}
				p.playCard(cardIndex);
				
				// Step 2: if nothing needs to be resolved, end turn and potentially end round
				if (p.getPhase() == Consts.PLAYCARD) {
					p.setPhase(Consts.OFFTURN);
					if (roundEnd()) {
						endRound();
					} else {
						curPlayer = (curPlayer+1)%players.size();
						players.get(curPlayer).startTurn();
						
					}
				}
			}
		}
		
		updatePlayers();
		updateBasicDB();
		
	}
	
	public void playerConfirmUDB(String username) {
		PopePlayer p = getPlayerByName(username);
		p.setConfirmed(true);
		boolean flag = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isConfirmed() == false) {
				flag = false;
				break;
			}
		}
		if (flag) {
			if (gameEnd()) {
				endGame();
			} else {
				startRound();
			}
			
		}
		updatePlayers();
		updateBasicDB();
	}
	// end of actual operations
	
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
		updateDB("endRoundMsg", endRoundMsg);
		int i;
		List<Integer> deckList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckList.add(deck.get(i).getNum());
		}
		updateDB("deck", deckList);
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
	public String getEndRoundMsg() {
		return endRoundMsg;
	}
	public void setEndRoundMsg(String endRoundMsg) {
		this.endRoundMsg = endRoundMsg;
	}
}
