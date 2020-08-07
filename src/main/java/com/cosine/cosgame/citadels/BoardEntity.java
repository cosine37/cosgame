package com.cosine.cosgame.citadels;

import java.util.List;

public class BoardEntity {
	List<String> playerNames;
	List<List<String>> built;
	List<String> hand;
	List<String> buildable;
	List<String> coins;
	
	String bank;

	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<List<String>> getBuilt() {
		return built;
	}
	public void setBuilt(List<List<String>> built) {
		this.built = built;
	}
	public List<String> getHand() {
		return hand;
	}
	public void setHand(List<String> hand) {
		this.hand = hand;
	}
	public List<String> getBuildable() {
		return buildable;
	}
	public void setBuildable(List<String> buildable) {
		this.buildable = buildable;
	}
	public List<String> getCoins() {
		return coins;
	}
	public void setCoins(List<String> coins) {
		this.coins = coins;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
}
