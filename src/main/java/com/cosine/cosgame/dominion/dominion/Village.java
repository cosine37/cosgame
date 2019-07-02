package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Village extends Card{
	public Village() {
		super();
		this.name = "Village";
		this.image = "/image/Dominion/cards/Dominion/Village.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 2;
		this.price = 3;
	}
}
