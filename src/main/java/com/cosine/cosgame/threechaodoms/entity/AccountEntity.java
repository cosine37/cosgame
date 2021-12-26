package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class AccountEntity {
	int money;
	int ingot;
	int key;
	List<SkinEntity> skins;
	List<CardEntity> skinCards;
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getIngot() {
		return ingot;
	}
	public void setIngot(int ingot) {
		this.ingot = ingot;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public List<SkinEntity> getSkins() {
		return skins;
	}
	public void setSkins(List<SkinEntity> skins) {
		this.skins = skins;
	}
	public List<CardEntity> getSkinCards() {
		return skinCards;
	}
	public void setSkinCards(List<CardEntity> skinCards) {
		this.skinCards = skinCards;
	}
	
}
