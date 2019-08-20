package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Witch extends Card{
	public Witch() {
		super();
		this.name = "Witch";
		this.image = "/image/Dominion/cards/Dominion/Witch.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_ATTACK] = true;
		this.card = 2;
		this.price = 5;
	}
	
	public Ask attack() {
		Ask ask = super.attack();
		ask.setCardName(name);
		if (board.getPileByTop("Curse") == null) {
			
		} else {
			board.gainToPlayerFromPile(player, board.getPileByTop("Curse"));
			log(player.getName() + " gains a Curse ", 1);
		}
		return ask;
	}
	
}
