package com.cosine.cosgame.hotpot;

import java.util.ArrayList;
import java.util.List;

public class Board {
	String id;
	String lord;
	int status;
	int phase;
	int round;
	List<Player> players;
	List<List<Chip>> supply;
	
	public Board() {
		players = new ArrayList<>();
		supply = new ArrayList<>();
	}

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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Chip>> getSupply() {
		return supply;
	}
	public void setSupply(List<List<Chip>> supply) {
		this.supply = supply;
	}
	
	
}
