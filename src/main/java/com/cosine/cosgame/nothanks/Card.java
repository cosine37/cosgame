package com.cosine.cosgame.nothanks;

public class Card {
	protected int num; //0 -> ?, -3 -> Money
	protected String name;
	protected String img;
	
	public Card() {
		
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
