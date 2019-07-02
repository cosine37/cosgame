package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Smithy extends Card{
	public Smithy() {
		super();
		this.name = "Smithy";
		this.image = "/image/Dominion/cards/Dominion/Smithy.png";
		this.types[INDEX_ACTION] = true;
		this.card = 3;
		this.price = 4;
	}
}
