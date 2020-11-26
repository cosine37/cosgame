package com.cosine.cosgame.gravepsycho;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String round;
	String leftover;
	String myDecision;
	String myIndex;
	
	List<String> playerNames;
	List<String> revealed;
	List<String> removed;
	List<String> decisions;
	List<String> stillIn;
	List<String> money;
	List<String> moneyThisTurn;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getLeftover() {
		return leftover;
	}
	public void setLeftover(String leftover) {
		this.leftover = leftover;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<String> getRevealed() {
		return revealed;
	}
	public void setRevealed(List<String> revealed) {
		this.revealed = revealed;
	}
	public List<String> getRemoved() {
		return removed;
	}
	public void setRemoved(List<String> removed) {
		this.removed = removed;
	}
	public List<String> getDecisions() {
		return decisions;
	}
	public void setDecisions(List<String> decisions) {
		this.decisions = decisions;
	}
	public List<String> getStillIn() {
		return stillIn;
	}
	public void setStillIn(List<String> stillIn) {
		this.stillIn = stillIn;
	}
	public List<String> getMoney() {
		return money;
	}
	public void setMoney(List<String> money) {
		this.money = money;
	}
	public List<String> getMoneyThisTurn() {
		return moneyThisTurn;
	}
	public void setMoneyThisTurn(List<String> moneyThisTurn) {
		this.moneyThisTurn = moneyThisTurn;
	}
	public String getMyDecision() {
		return myDecision;
	}
	public void setMyDecision(String myDecision) {
		this.myDecision = myDecision;
	}
	public String getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(String myIndex) {
		this.myIndex = myIndex;
	}
	
}
