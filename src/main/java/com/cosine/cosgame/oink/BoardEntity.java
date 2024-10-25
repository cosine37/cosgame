package com.cosine.cosgame.oink;

import java.util.List;

import com.cosine.cosgame.oink.startups.entity.StartupsEntity;

public class BoardEntity {
	String id;
	String lord;
	int game;
	int status;
	
	List<String> playerNames;

	StartupsEntity startups;
	
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
	public int getGame() {
		return game;
	}
	public void setGame(int game) {
		this.game = game;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public StartupsEntity getStartups() {
		return startups;
	}
	public void setStartups(StartupsEntity startups) {
		this.startups = startups;
	}
}
