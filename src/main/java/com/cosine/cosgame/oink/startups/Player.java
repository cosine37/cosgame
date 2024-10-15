package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name;
	int index;
	int coins;
	int phase;
	
	int numTaken;
	
	int coin1RoundEnd;
	int coin3RoundEnd;
	
	List<Integer> scores;
	
	List<Card> hand;
	List<Card> play;
	
	Startups board;
	
	public void startRound() {
		numTaken = -1;
		phase = Consts.DRAWORTAKE;
	}
	
	public void draw() {
		if (canDraw()) {
			// Step 1: pay coins
			int cost = board.drawCost(this);
			coins = coins-cost;
			board.placeCoins(this);
			
			// Step 2: put the card in hand
			Card c = board.removeTopCard();
			if (c != null) hand.add(c);
			
			
			// Step 3: next phase
			if (board.getStatus() == Consts.INGAME) {
				phase = Consts.PLAYORDISCARD;
			}
		}
	}
	
	public boolean canDraw() {
		int cost = board.drawCost(this);
		if (cost>coins) {
			return false;
		} else {
			return true;
		}
	}
	
	public void take(int index) {
		if (canTake(index)) {
			// Step 1: receive coins
			int cost = board.getDiscard().get(index).getCoinOn();
			coins = coins+cost;
			
			// Step 2: put the card in hand
			Card c = board.getDiscard().remove(index);
			if (c != null) hand.add(c);
			
			// Step 3: next phase
			if (board.getStatus() == Consts.INGAME) {
				phase = Consts.PLAYORDISCARD;
			}
		}
	}
	
	public boolean canTake(int index) {
		if (index < board.getDiscard().size()) {
			Card c = board.getDiscard().get(index);
			if (board.getAntiMonopoly().get(c.getNum()) == index) { // if player has antimonopoly token
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public void play(int index) { // index is where from hand
		if (index < hand.size()) {
			int i;
			boolean flag = true;
			Card c = hand.remove(index);
			for (i=0;i<hand.size();i++) {
				if (play.get(i).getNum() == c.getNum()) {
					flag = false;
					play.add(i,c);
				}
			}
			if (flag) {
				play.add(c);
			}
			
			
			if (board.getStatus() == Consts.INGAME) {
				phase = Consts.OFFTURN;
				
				// TODO: add end round handles here
				board.nextPlayer();
			}
		}
	}
	
	public void discard(int index) { // index is where from hand
		if (index < hand.size()) {
			Card c = hand.remove(index);
			board.getDiscard().add(c);
			
			if (board.getStatus() == Consts.INGAME) {
				phase = Consts.OFFTURN;
				
				// TODO: add end round handles here
				board.nextPlayer();
			}
		}
	}
	
	public void emptyHandPlay() {
		hand = new ArrayList<>();
		play = new ArrayList<>();
	}
	
	public int numStock(int stock) {
		int ans = 0;
		int i;
		for (i=0;i<play.size();i++) {
			if (play.get(i).getNum() == stock) {
				ans++;
			}
		}
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() == stock) {
				ans++;
			}
		}
		return ans;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getCoin1RoundEnd() {
		return coin1RoundEnd;
	}
	public void setCoin1RoundEnd(int coin1RoundEnd) {
		this.coin1RoundEnd = coin1RoundEnd;
	}
	public int getCoin3RoundEnd() {
		return coin3RoundEnd;
	}
	public void setCoin3RoundEnd(int coin3RoundEnd) {
		this.coin3RoundEnd = coin3RoundEnd;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public Startups getBoard() {
		return board;
	}
	public void setBoard(Startups board) {
		this.board = board;
	}
	
	
}
