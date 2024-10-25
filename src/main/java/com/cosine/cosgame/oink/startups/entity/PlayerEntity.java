package com.cosine.cosgame.oink.startups.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	int index;
	int coins;
	int phase;
	int numTaken;
	int coin1RoundEnd;
	int coin3RoundEnd;
	
	List<Integer> scores;
	List<CardEntity> play;

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
	public int getNumTaken() {
		return numTaken;
	}
	public void setNumTaken(int numTaken) {
		this.numTaken = numTaken;
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
	public List<CardEntity> getPlay() {
		return play;
	}
	public void setPlay(List<CardEntity> play) {
		this.play = play;
	}
	
	
	

}
