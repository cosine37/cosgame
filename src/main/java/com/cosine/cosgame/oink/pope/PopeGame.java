package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.oink.Board;

public class PopeGame {
	int round;
	int firstPlayer;
	int curPlayer;
	
	Board board;
	List<PopePlayer> players;
	List<Card> deck;
	
	public PopeGame() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
	}
	
	public Card removeFromDeck() {
		if (deck.size()>0) {
			Card c = deck.remove(0);
			return c;
		} else {
			return null;
		}
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
