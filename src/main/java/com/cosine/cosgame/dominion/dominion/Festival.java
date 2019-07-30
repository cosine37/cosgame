package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Festival extends Card{
	public Festival() {
		super();
		this.name = "Festival";
		this.image = "/image/Dominion/cards/Dominion/Festival.png";
		this.types[INDEX_ACTION] = true;
		this.action = 2;
		this.buy = 1;
		this.coin = 2;
		this.price = 5;
	}
}
