package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.oink.Board;

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
	
	public West() {
		
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
