package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Market extends Card{
	public Market() {
		super();
		this.name = "Market";
		this.image = "/image/Dominion/cards/Dominion/Market.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.buy = 1;
		this.coin = 1;
		this.price = 5;
	}
}
