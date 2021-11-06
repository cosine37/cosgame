package com.cosine.cosgame.gardenwar.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	int status;
	List<PlayerEntity> players;
	
	List<CardEntity> myHand;

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
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public List<CardEntity> getMyHand() {
		return myHand;
	}
	public void setMyHand(List<CardEntity> myHand) {
		this.myHand = myHand;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
