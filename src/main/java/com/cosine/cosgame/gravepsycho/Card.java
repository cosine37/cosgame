package com.cosine.cosgame.gravepsycho;

public class Card {
	String name;
	String img;
	int type;
	int num;
	
	public Card() {
		
	}
	
	public Card(String img, int type, int num) {
		this();
		this.img = img;
		this.type = type;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return img;
	}
	public void setImage(String img) {
		this.img = img;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
