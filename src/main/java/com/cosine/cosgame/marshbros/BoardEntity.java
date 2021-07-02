package com.cosine.cosgame.marshbros;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String curPlayerIndex;
	String myIndex;
	
	List<String> players;
	List<String> hand;
	List<String> diceResults;
	List<List<String>> areaCards;
	List<List<String>> atks;
	List<List<String>> hps;
	List<List<String>> choices;

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
	public List<String> getPlayers() {
		return players;
	}
	public void setPlayers(List<String> players) {
		this.players = players;
	}
	public List<String> getHand() {
		return hand;
	}
	public void setHand(List<String> hand) {
		this.hand = hand;
	}
	public String getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(String curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public String getMyIndex() {
		return myIndex;
	}
	public void setMyIndex(String myIndex) {
		this.myIndex = myIndex;
	}
	public List<List<String>> getAreaCards() {
		return areaCards;
	}
	public void setAreaCards(List<List<String>> areaCards) {
		this.areaCards = areaCards;
	}
	public List<List<String>> getAtks() {
		return atks;
	}
	public void setAtks(List<List<String>> atks) {
		this.atks = atks;
	}
	public List<List<String>> getHps() {
		return hps;
	}
	public void setHps(List<List<String>> hps) {
		this.hps = hps;
	}
	public List<String> getDiceResults() {
		return diceResults;
	}
	public void setDiceResults(List<String> diceResults) {
		this.diceResults = diceResults;
	}
	public List<List<String>> getChoices() {
		return choices;
	}
	public void setChoices(List<List<String>> choices) {
		this.choices = choices;
	}
	
	
	
}
