package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Caravan extends Card{
	public Caravan() {
		super();
		this.name = "Caravan";
		this.image = "/image/Dominion/cards/Seaside/Caravan.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 4;
		this.card = 1;
		this.action = 1;
		this.nt = 1;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.draw(1);
		log(player.getName() + " draws a card (from Caravan)", 1);
		return ask;
	}
	
}
