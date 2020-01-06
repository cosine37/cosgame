package com.cosine.cosgame.coslash;

public class Card {
	public static final int BASIC = 0;
	public static final int ACTION = 1;
	public static final int EQUIP = 2;
	public static final int DELAYED = 3;
	int type;
	String name;
	String text;
	
	public Card() {
		
	}
	
	// 使用
	public void play(Player p, Table t) { 
		
	}
	
	// 打出
	public void respond(Player p, Table t) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
