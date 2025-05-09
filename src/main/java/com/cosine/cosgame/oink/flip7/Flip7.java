package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.oink.Board;

public class Flip7 {
	List<Card> deck;
	List<Card> discard;
	List<Flip7Player> players;
	int round;
	int firstPlayer;
	
	Board board;
	
	public Flip7() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		discard = new ArrayList<>();
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
	
}
