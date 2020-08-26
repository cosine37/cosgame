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
	List<String> roleNums;
	List<String> roleOwners;
	List<String> roleRevealed;
	List<String> logs;
	
	String phase;
	String bank;
	String deckSize;
	String status;
	String roundCount;
	String curPlayer;
	String curRole;
	String crown;
	String lord;
	String isLord;
	String id;
	String lastRound;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(String roundCount) {
		this.roundCount = roundCount;
	}
	public String getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(String curPlayer) {
		this.curPlayer = curPlayer;
	}
	public String getCurRole() {
		return curRole;
	}
	public void setCurRole(String curRole) {
		this.curRole = curRole;
	}
	public List<String> getRoleNums() {
		return roleNums;
	}
	public void setRoleNums(List<String> roleNums) {
		this.roleNums = roleNums;
	}
	public List<String> getRoleOwners() {
		return roleOwners;
	}
	public void setRoleOwners(List<String> roleOwners) {
		this.roleOwners = roleOwners;
	}
	public String getCrown() {
		return crown;
	}
	public void setCrown(String crown) {
		this.crown = crown;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getIsLord() {
		return isLord;
	}
	public void setIsLord(String isLord) {
		this.isLord = isLord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastRound() {
		return lastRound;
	}
	public void setLastRound(String lastRound) {
		this.lastRound = lastRound;
	}
	public List<String> getRoleRevealed() {
		return roleRevealed;
	}
	public void setRoleRevealed(List<String> roleRevealed) {
		this.roleRevealed = roleRevealed;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
}
