package com.cosine.cosgame.pokerworld.horserace;

public class Horse extends GridItem{
	public String suit;
	
	public Horse(String suit) {
		super();
		this.suit = suit;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	
}
