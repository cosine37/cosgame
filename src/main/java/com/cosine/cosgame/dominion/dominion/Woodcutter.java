package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Woodcutter extends Card{
	public Woodcutter() {
		super();
		this.name = "Woodcutter";
		this.image = "/image/Dominion/cards/Dominion/Woodcutter.png";
		this.types[INDEX_ACTION] = true;
		this.buy = 1;
		this.coin = 2;
		this.price = 3;
	}
}
