package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	String playedCards;
	boolean confirmedNextTurn;
	List<Integer> scores;
	List<Integer> bids;
	List<Integer> actuals;
	List<Integer> chosenSkins;
	List<String> rewards;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(String playedCards) {
		this.playedCards = playedCards;
	}
	public boolean isConfirmedNextTurn() {
		return confirmedNextTurn;
	}
	public void setConfirmedNextTurn(boolean confirmedNextTurn) {
		this.confirmedNextTurn = confirmedNextTurn;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<Integer> getBids() {
		return bids;
	}
	public void setBids(List<Integer> bids) {
		this.bids = bids;
	}
	public List<Integer> getActuals() {
		return actuals;
	}
	public void setActuals(List<Integer> actuals) {
		this.actuals = actuals;
	}
	public List<Integer> getChosenSkins() {
		return chosenSkins;
	}
	public void setChosenSkins(List<Integer> chosenSkins) {
		this.chosenSkins = chosenSkins;
	}
	public List<String> getRewards() {
		return rewards;
	}
	public void setRewards(List<String> rewards) {
		this.rewards = rewards;
	}
	
}
