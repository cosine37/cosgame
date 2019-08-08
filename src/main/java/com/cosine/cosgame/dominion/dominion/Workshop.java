package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Workshop extends Card{
	public Workshop() {
		super();
		this.name = "Workshop";
		this.image = "/image/Dominion/cards/Dominion/Workshop.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
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
		board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
		ask = new Ask();
		return ask;
	}
}
