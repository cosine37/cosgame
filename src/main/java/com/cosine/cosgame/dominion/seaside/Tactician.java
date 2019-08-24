package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Tactician extends Card{
	public Tactician() {
		super();
		this.name = "Tactician";
		this.image = "/image/Dominion/cards/Seaside/Tactician.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size()>0) {
			this.numTurns = 1;
			player.discardHand();
			log(player.getName() + " discards hand", 1);
		}
		return ask;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.draw(5);
		player.addAction(1);
		player.addBuy(1);
		log(player.getName() + " gets +5 Cards, +1 Action, +1 Buy (from Tactician)", 1);
		return ask;
	}
	
}
