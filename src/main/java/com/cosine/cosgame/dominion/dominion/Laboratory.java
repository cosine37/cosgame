package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Laboratory extends Card{
	public Laboratory() {
		super();
		this.name = "Laboratory";
		this.image = "/image/Dominion/cards/Dominion/Laboratory.png";
		this.types[INDEX_ACTION] = true;
		this.card = 2;
		this.action = 1;
		this.price = 5;
	}
}
