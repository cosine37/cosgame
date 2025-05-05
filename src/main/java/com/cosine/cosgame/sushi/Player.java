package com.cosine.cosgame.sushi;

import java.util.List;

public class Player {
	int phase;
	List<Card> hand;
	List<Card> play;
	List<Integer> scores;
	Card chosen;
	
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
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
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public Card getChosen() {
		return chosen;
	}
	public void setChosen(Card chosen) {
		this.chosen = chosen;
	}
}
