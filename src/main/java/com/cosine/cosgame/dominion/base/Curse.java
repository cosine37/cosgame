package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Curse extends Card{
	public Curse() {
		super();
		this.name = "Curse";
		this.image = "/image/Dominion/cards/Base/Curse.png";
		this.types[INDEX_CURSED] = true;
		this.score = -1;
		this.price = 0;
	}
}
