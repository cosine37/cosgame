package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class Ironworks extends Card{
	public Ironworks() {
		super();
		this.name = "Ironworks";
		this.image = "/image/Dominion/cards/Intrigue/Ironworks.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setMsg("Gain a card costing up to $4");
		ask.setType(Ask.GAIN);
		ask.setUpper(4);
		ask.setLower(0);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String gainedCardName = ask.getSelectedCards().get(0);
		log(player.getName() + " gains a " + gainedCardName, 1);
		board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
		CardFactory factory = new CardFactory();
		Card gainedCard = factory.createCard(gainedCardName);
		if (gainedCard.isActionType()) {
			player.addAction(1);
			log(player.getName() + " gets +1 Action", 1);
		}
		if (gainedCard.isTreasure()) {
			player.addCoin(1);
			log(player.getName() + " gets + $1", 1);
		}
		if (gainedCard.isVictory()) {
			player.draw(1);;
			log(player.getName() + " draws a card", 1);
		}
		ask = new Ask();
		return ask;
	}
}
