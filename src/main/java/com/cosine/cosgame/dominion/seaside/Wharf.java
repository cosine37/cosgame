package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Wharf extends Card{
	public Wharf() {
		super();
		this.name = "Wharf";
		this.image = "/image/Dominion/cards/Seaside/Wharf.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 5;
		this.card = 2;
		this.buy = 1;
		this.nt = 1;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.draw(2);
		player.addBuy(1);
		log(player.getName() + " gets +2 Cards, +1 Buy (from Wharf)", 1);
		return ask;
	}
	
}
