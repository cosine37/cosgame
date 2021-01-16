package com.cosine.cosgame.towermaker;

public class Monster {
	int bp;
	String img;
	String name;
	
	public Monster() {
		
	}
	
	public boolean isEven() {
		if (bp%2 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getBp() {
		return bp;
	}
	public void setBp(int bp) {
		this.bp = bp;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
