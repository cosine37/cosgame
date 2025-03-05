package com.cosine.cosgame.rich.entity;

public class SettingsEntity {
	int mode;
	int startMoney;
	int startSalary;
	int endCondition;
	
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
}
