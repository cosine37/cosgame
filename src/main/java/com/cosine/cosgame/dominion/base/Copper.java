package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Copper extends Card{
	public Copper() {
		super();
		this.name = "Copper";
		this.image = "/image/Dominion/cards/Base/Copper.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 1;
		this.price = 0;
	}
}
