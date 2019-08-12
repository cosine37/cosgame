package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Card;

public class Moat extends Card{
	public Moat() {
		super();
		this.name = "Moat";
		this.image = "/image/Dominion/cards/Dominion/Moat.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_REACTION] = true;
		this.types[INDEX_ATTACKBLOCK] = true;
		
		this.card = 2;
		this.price = 2;
	}
	
	
	
}
