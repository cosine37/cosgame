package com.cosine.cosgame.rich.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	int money;
	int salary;
	int phase;
	int index;
	int rollDisplay;
	int jailRound;
	boolean inJail;
	boolean confirmed;
	
	List<Integer> owned;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<Integer> getOwned() {
		return owned;
	}
	public void setOwned(List<Integer> owned) {
		this.owned = owned;
	}
	public int getRollDisplay() {
		return rollDisplay;
	}
	public void setRollDisplay(int rollDisplay) {
		this.rollDisplay = rollDisplay;
	}
	public int getJailRound() {
		return jailRound;
	}
	public void setJailRound(int jailRound) {
		this.jailRound = jailRound;
	}
	public boolean isInJail() {
		return inJail;
	}
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	
}
