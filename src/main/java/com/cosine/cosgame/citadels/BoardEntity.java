package com.cosine.cosgame.citadels;

import java.util.List;

public class BoardEntity {
	List<String> playerNames;
	List<List<String>> built;
	List<List<String>> hand;
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
	public List<List<String>> getHand() {
		return hand;
	}
	public void setHand(List<List<String>> hand) {
		this.hand = hand;
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
