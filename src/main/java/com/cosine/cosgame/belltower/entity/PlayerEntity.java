package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	int role;
	List<Integer> extraBits;
	List<String> logs;
	boolean alive;
	boolean canVote;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public List<Integer> getExtraBits() {
		return extraBits;
	}
	public void setExtraBits(List<Integer> extraBits) {
		this.extraBits = extraBits;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isCanVote() {
		return canVote;
	}
	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
	
}
