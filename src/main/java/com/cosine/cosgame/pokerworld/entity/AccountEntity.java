package com.cosine.cosgame.pokerworld.entity;

import java.util.List;

public class AccountEntity {
	String name;
	int money;
	int diamond;
	int key;
	int goldenKey;
	List<List<String>> allSkinImgs;
	List<List<String>> allSkinNames;
	List<String> chosenSkins;
	
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
	public int getGoldenKey() {
		return goldenKey;
	}
	public void setGoldenKey(int goldenKey) {
		this.goldenKey = goldenKey;
	}
	public List<List<String>> getAllSkinImgs() {
		return allSkinImgs;
	}
	public void setAllSkinImgs(List<List<String>> allSkinImgs) {
		this.allSkinImgs = allSkinImgs;
	}
	public List<List<String>> getAllSkinNames() {
		return allSkinNames;
	}
	public void setAllSkinNames(List<List<String>> allSkinNames) {
		this.allSkinNames = allSkinNames;
	}
	public List<String> getChosenSkins() {
		return chosenSkins;
	}
	public void setChosenSkins(List<String> chosenSkins) {
		this.chosenSkins = chosenSkins;
	}
	
}
