package com.cosine.cosgame.rich.entity;

public class SettingsEntity {
	int mode;
	int startMoney;
	int startSalary;
	int endCondition;
	int firstPlayer;
	int mapId;
	int useGTA;
	int useNEW;
	
	public int getStartMoney() {
		return startMoney;
	}
	public void setStartMoney(int startMoney) {
		this.startMoney = startMoney;
	}
	public int getStartSalary() {
		return startSalary;
	}
	public void setStartSalary(int startSalary) {
		this.startSalary = startSalary;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getEndCondition() {
		return endCondition;
	}
	public void setEndCondition(int endCondition) {
		this.endCondition = endCondition;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getUseGTA() {
		return useGTA;
	}
	public void setUseGTA(int useGTA) {
		this.useGTA = useGTA;
	}
	public int getUseNEW() {
		return useNEW;
	}
	public void setUseNEW(int useNEW) {
		this.useNEW = useNEW;
	}
}
