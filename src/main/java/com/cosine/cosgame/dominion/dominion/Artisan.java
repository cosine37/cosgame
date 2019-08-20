package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Artisan extends Card{
	public Artisan() {
		super();
		this.name = "Artisan";
		this.image = "/image/Dominion/cards/Dominion/Artisan.png";
		this.types[INDEX_ACTION] = true;
		this.price = 6;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setMsg("Gain a card costing up to $5 to your hand");
		ask.setType(Ask.GAIN);
		ask.setUpper(5);
		ask.setLower(0);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getResLevel() == 0) {
			String gainedCardName = ask.getSelectedCards().get(0);
			log(player.getName() + " gains a " + gainedCardName + " to hand", 1);
			board.gainToPlayerFromPileToHand(player, board.getPileByTop(gainedCardName));
			ask = new Ask();
			ask.setCardName(name);
			ask.setMsg("Put a card on top of your deck");
			ask.setType(Ask.HANDCHOOSE);
			ask.setLower(1);
			ask.setUpper(1);
			ask.setResLevel(1);
		} else if (ask.getResLevel() == 1) {
			String cardName = ask.getSelectedCards().get(0);
			player.topDeck(cardName);
			log(player.getName() + " topdecks a card", 1);
			ask = new Ask();
			ask.setCardName(name);
			ask.setResLevel(2);
		} else {
			ask = new Ask();
		}
		return ask;
	}
}
