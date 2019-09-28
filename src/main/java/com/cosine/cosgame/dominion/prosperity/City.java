package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class City extends Card{
	public City() {
		super();
		this.name = "City";
		this.image = "/image/Dominion/cards/Prosperity/City.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
		this.card = 1;
		this.action = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		int emptyPile = board.getNumEmptyPile();
		if (emptyPile == 1) {
			player.draw(1);
			log(player.getName() + " gets an extra +1 Card", 1);
		} else if (emptyPile > 1) {
			player.draw(1);
			log(player.getName() + " gets an extra +1 Card", 1);
			player.addCoin(1);
			player.addBuy(1);
			log(player.getName() + " gets extra +1 Buy, + $1", 1);
		}
		return ask;
	}
}
