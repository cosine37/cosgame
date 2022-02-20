package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class AccountEntity {
	String name;
	int money;
	int diamond;
	int key;
	List<String> availableCharacters;
	
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
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public List<String> getAvailableCharacters() {
		return availableCharacters;
	}
	public void setAvailableCharacters(List<String> availableCharacters) {
		this.availableCharacters = availableCharacters;
	}
	
}
