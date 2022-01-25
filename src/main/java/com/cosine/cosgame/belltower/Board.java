package com.cosine.cosgame.belltower;

import java.util.List;

public class Board {
	String id;
	String lord;
	int status;
	int phase;
	int numDay;
	
	List<Player> players;
	Script script;
	
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Script getScript() {
		return script;
	}
	public void setScript(Script script) {
		this.script = script;
	}
}
