package com.cosine.cosgame.rich.entity;

import java.util.List;

public class BoardEntity {
	String id;
	String lord;
	String myNextPlace;
	
	int status;
	int round;
	int mode;
	int phase;
	int lastRolled;
	
	List<PlayerEntity> players;
	List<String> myOptions;
	MapEntity map;
	SettingsEntity settings;
	
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
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public MapEntity getMap() {
		return map;
	}
	public void setMap(MapEntity map) {
		this.map = map;
	}
	public SettingsEntity getSettings() {
		return settings;
	}
	public void setSettings(SettingsEntity settings) {
		this.settings = settings;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getLastRolled() {
		return lastRolled;
	}
	public void setLastRolled(int lastRolled) {
		this.lastRolled = lastRolled;
	}
	public List<String> getMyOptions() {
		return myOptions;
	}
	public void setMyOptions(List<String> myOptions) {
		this.myOptions = myOptions;
	}
	public String getMyNextPlace() {
		return myNextPlace;
	}
	public void setMyNextPlace(String myNextPlace) {
		this.myNextPlace = myNextPlace;
	}
	
	
}
