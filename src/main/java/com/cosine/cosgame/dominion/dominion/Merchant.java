package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Merchant extends Card{
	public Merchant() {
		super();
		this.name = "Merchant";
		this.image = "/image/Dominion/cards/Dominion/Merchant.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
		this.card = 1;
		this.action = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.addPlayed(name);
		return ask;
	}
}
