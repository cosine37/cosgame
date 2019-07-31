package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Empty extends Card{
	public Empty() {
		super();
		this.name = "Empty";
		this.image = "/image/Dominion/cards/Empty.png";
		this.price = 0;
	}
}
