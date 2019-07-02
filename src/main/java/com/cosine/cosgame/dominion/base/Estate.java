package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Estate extends Card{
	public Estate() {
		super();
		this.name = "Estate";
		this.image = "/image/Dominion/cards/Base/Estate.png";
		this.types[INDEX_VICTORY] = true;
		this.score = 1;
		this.price = 2;
	}
}
