package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Province extends Card{
	public Province() {
		super();
		this.name = "Province";
		this.image = "/image/Dominion/cards/Base/Province.png";
		this.types[INDEX_VICTORY] = true;
		this.score = 6;
		this.price = 8;
	}
}
