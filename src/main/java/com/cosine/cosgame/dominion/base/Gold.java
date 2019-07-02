package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Gold extends Card{
	public Gold() {
		super();
		this.name = "Gold";
		this.image = "/image/Dominion/cards/Base/Gold.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 3;
		this.price = 6;
	}
}
