package com.cosine.cosgame.pokerworld.camelup;

public class Gambler {
	String name;
	int index;
	
	public Gambler() {
		
	}
	public Gambler(String name) {
		this.name = name;
	}
	public Gambler(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
}
