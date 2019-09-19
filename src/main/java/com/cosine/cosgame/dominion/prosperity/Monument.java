package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Card;

public class Monument extends Card{
	public Monument() {
		super();
		this.name = "Monument";
		this.image = "/image/Dominion/cards/Prosperity/Monument.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.coin = 2;
		this.vp = 1;
	}
}
