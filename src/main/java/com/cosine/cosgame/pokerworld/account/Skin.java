package com.cosine.cosgame.pokerworld.account;

public class Skin {
	int id;
	int type;
	int level;
	String name;
	
	public Skin() {
		
	}
	
	public Skin(int id, int type, int level, String name) {
		this.id = id;
		this.type = type;
		this.level = level;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
