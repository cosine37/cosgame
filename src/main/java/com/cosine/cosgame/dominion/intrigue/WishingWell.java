package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class WishingWell extends Card{
	public WishingWell() {
		super();
		this.name = "WishingWell";
		this.image = "/image/Dominion/cards/Intrigue/WishingWell.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.GUESS);
		ask.setMsg("Guess a card");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		Card card = player.getTop();
		String guessed = ask.getSelectedCards().get(0);
		if (card.getName().toUpperCase().equals(guessed.toUpperCase().replaceAll(" ", ""))) {
			log(player.getName() + " wishes " + card.getName() + " and finds it!",1);
			player.draw(1);
		} else {
			log(player.getName() + " wishes " + guessed + " but reveals "+ card.getName(),1);
		}
		ask = new Ask();
		return ask;
	}
}
