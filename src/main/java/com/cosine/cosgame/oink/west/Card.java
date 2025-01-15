package com.cosine.cosgame.oink.west;

public class Card {
	int num;
	String name;
	String desc;
	
	public Card(int num, String name, String desc) {
		this.num = num;
		this.name = name;
		this.desc = desc;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
