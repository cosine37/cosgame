package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Card;

public class Duchy extends Card{
	public Duchy() {
		super();
		this.name = "Duchy";
		this.image = "/image/Dominion/cards/Base/Duchy.png";
		this.types[INDEX_VICTORY] = true;
		this.score = 3;
		this.price = 5;
	}
}
