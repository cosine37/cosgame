package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class PendantForWine extends Card{
	public PendantForWine() {
		super();
		this.name = "PendantForWine";
		this.image = "/image/Dominion/cards/Oriental/PendantForWine.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		
		this.safe = false;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size()>0) {
			player.addCoin(5);
			log(player.getName() + " discards hand for +$5", 1);
			player.discardHand();
		}
		return ask;
	}
}
