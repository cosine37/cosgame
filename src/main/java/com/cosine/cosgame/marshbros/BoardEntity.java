package com.cosine.cosgame.marshbros;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String phase;
	String curPlayerIndex;
	String myIndex;
	String canStealOrRaid;
	
	List<String> players;
	List<String> hand;
	List<String> diceResults;
	List<String> resources;
	List<String> logs;
	List<String> richest;
	List<List<RoleEntity>> roles;
	
	AskEntity ask;

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
	public List<String> getDiceResults() {
		return diceResults;
	}
	public void setDiceResults(List<String> diceResults) {
		this.diceResults = diceResults;
	}
	public List<String> getResources() {
		return resources;
	}
	public void setResources(List<String> resources) {
		this.resources = resources;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public List<List<RoleEntity>> getRoles() {
		return roles;
	}
	public void setRoles(List<List<RoleEntity>> roles) {
		this.roles = roles;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public AskEntity getAsk() {
		return ask;
	}
	public void setAsk(AskEntity ask) {
		this.ask = ask;
	}
	public List<String> getRichest() {
		return richest;
	}
	public void setRichest(List<String> richest) {
		this.richest = richest;
	}
	public String getCanStealOrRaid() {
		return canStealOrRaid;
	}
	public void setCanStealOrRaid(String canStealOrRaid) {
		this.canStealOrRaid = canStealOrRaid;
	}
	
}
