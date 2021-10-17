package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String status;
	String myCards;
	
	List<PlayerEntity> players;

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
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public String getMyCards() {
		return myCards;
	}
	public void setMyCards(String myCards) {
		this.myCards = myCards;
	}
	
}
