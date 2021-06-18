package com.cosine.cosgame.marshbros;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String curPlayerIndex;
	
	List<String> players;
	List<String> hand;

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
	
	
	
}
