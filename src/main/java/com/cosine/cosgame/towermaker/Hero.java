package com.cosine.cosgame.towermaker;

import java.util.List;

public class Hero {
	int hp;
	String name;
	String img;
	List<Equip> equips;
	
	public void setInitialEquips() {
		
	}
	
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public List<Equip> getEquips() {
		return equips;
	}
	public void setEquips(List<Equip> equips) {
		this.equips = equips;
	}
}
