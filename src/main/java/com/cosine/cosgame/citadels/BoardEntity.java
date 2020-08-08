package com.cosine.cosgame.citadels;

import java.util.List;

public class BoardEntity {
	List<String> playerNames;
	List<List<String>> built;
	List<String> hand;
	List<String> buildable;
	List<String> coins;
	List<String> handSizes;
	List<String> revealedCards;
	
	String phase;
	String bank;
	String deckSize;

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
	public List<String> getHandSizes() {
		return handSizes;
	}
	public void setHandSizes(List<String> handSizes) {
		this.handSizes = handSizes;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public List<String> getRevealedCards() {
		return revealedCards;
	}
	public void setRevealedCards(List<String> revealedCards) {
		this.revealedCards = revealedCards;
	}
	public String getDeckSize() {
		return deckSize;
	}
	public void setDeckSize(String deckSize) {
		this.deckSize = deckSize;
	}
}
