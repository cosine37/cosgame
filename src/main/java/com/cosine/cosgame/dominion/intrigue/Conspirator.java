package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Conspirator extends Card{
	public Conspirator() {
		super();
		this.name = "Conspirator";
		this.image = "/image/Dominion/cards/Intrigue/Conspirator.png";
		this.types[INDEX_ACTION] = true;
		this.coin = 2;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getNumActionsPlayed() > 2) {
			player.draw(1);
			player.addAction(1);
			log(player.getName() + " gets +1 Card, +1 Action", 1);
		} else {
			
		}
		return ask;
	}
}
