package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class BoardEntity {
	String id;
	String lord;
	String myNextPlace;
	String myLandMsg;
	String fateMsg;
	String broadcastMsg;
	String broadcastImg;
	Map<String, String> broadcastImgStyle;
	
	int status;
	int round;
	int mode;
	int phase;
	int lastRolled;
	int curPlayer;
	int jailRound;
	int endCondition;
	
	boolean inJail;
	
	List<PlayerEntity> players;
	List<String> myOptions;
	List<String> logs;
	MapEntity map;
	SettingsEntity settings;
	PlaceEntity myCurrentPlace;
	
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
	public PlaceEntity getMyCurrentPlace() {
		return myCurrentPlace;
	}
	public void setMyCurrentPlace(PlaceEntity myCurrentPlace) {
		this.myCurrentPlace = myCurrentPlace;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public String getMyLandMsg() {
		return myLandMsg;
	}
	public void setMyLandMsg(String myLandMsg) {
		this.myLandMsg = myLandMsg;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public String getFateMsg() {
		return fateMsg;
	}
	public void setFateMsg(String fateMsg) {
		this.fateMsg = fateMsg;
	}
	public boolean isInJail() {
		return inJail;
	}
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	public int getJailRound() {
		return jailRound;
	}
	public void setJailRound(int jailRound) {
		this.jailRound = jailRound;
	}
	public String getBroadcastMsg() {
		return broadcastMsg;
	}
	public void setBroadcastMsg(String broadcastMsg) {
		this.broadcastMsg = broadcastMsg;
	}
	public String getBroadcastImg() {
		return broadcastImg;
	}
	public void setBroadcastImg(String broadcastImg) {
		this.broadcastImg = broadcastImg;
	}
	public Map<String, String> getBroadcastImgStyle() {
		return broadcastImgStyle;
	}
	public void setBroadcastImgStyle(Map<String, String> broadcastImgStyle) {
		this.broadcastImgStyle = broadcastImgStyle;
	}
	public int getEndCondition() {
		return endCondition;
	}
	public void setEndCondition(int endCondition) {
		this.endCondition = endCondition;
	}
	

}
