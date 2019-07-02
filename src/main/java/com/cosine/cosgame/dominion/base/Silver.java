package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Silver extends Card{
	public Silver() {
		super();
		this.name = "Silver";
		this.image = "/image/Dominion/cards/Base/Silver.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 2;
		this.price = 3;
	}
}
