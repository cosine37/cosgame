package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	int status;
	int phase;
	int numDay;
	
	RoleEntity myRole;
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumDay() {
		return numDay;
	}
	public void setNumDay(int numDay) {
		this.numDay = numDay;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public RoleEntity getMyRole() {
		return myRole;
	}
	public void setMyRole(RoleEntity myRole) {
		this.myRole = myRole;
	}
	
}
