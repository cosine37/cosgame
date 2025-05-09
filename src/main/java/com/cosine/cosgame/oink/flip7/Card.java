package com.cosine.cosgame.oink.flip7;

public class Card {
	int num;
	String name;
	String desc;
	
	public Card() {
		num = -1;
		desc = "";
	}
	
	public Card(int num, String name) {
		this();
		this.num = num;
		this.name = name;
	}
	
	public Card(int num, String name, String desc) {
		this.num = num;
		this.desc = desc;
		this.name = name;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
