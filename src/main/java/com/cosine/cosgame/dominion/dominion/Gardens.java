package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Gardens extends Card{
	public Gardens() {
		super();
		this.name = "Gardens";
		this.image = "/image/Dominion/cards/Dominion/Gardens.png";
		this.types[INDEX_VICTORY] = true;
		this.price = 4;
	}
	
	public int getScore() {
		int score = player.getAllCards().size() / 10;
		return score;
	}
}
