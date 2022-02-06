package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String morningMsg;
	String myDisplayName;
	int status;
	int phase;
	int numDay;
	
	RoleEntity myRole;
	String myLastNightMsg;
	List<String> myLogs;
	
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
	public String getMyLastNightMsg() {
		return myLastNightMsg;
	}
	public void setMyLastNightMsg(String myLastNightMsg) {
		this.myLastNightMsg = myLastNightMsg;
	}
	public List<String> getMyLogs() {
		return myLogs;
	}
	public void setMyLogs(List<String> myLogs) {
		this.myLogs = myLogs;
	}
	public String getMorningMsg() {
		return morningMsg;
	}
	public void setMorningMsg(String morningMsg) {
		this.morningMsg = morningMsg;
	}
	public String getMyDisplayName() {
		return myDisplayName;
	}
	public void setMyDisplayName(String myDisplayName) {
		this.myDisplayName = myDisplayName;
	}
	
}
