package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Exile extends Card{
	public Exile() {
		super();
		this.name = "Exile";
		this.image = "/image/Dominion/cards/Oriental/Exile.png";
		this.types[INDEX_ACTION] = true;
		this.card = 2;
		this.price = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(4);
		ask.setLower(0);
		ask.setMsg("You may put up to 4 cards from your hand to your Seclusion mat");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		player.setBoard(board);
		player.exile(ask.getSelectedCards(), "hand");
		log(player.getName() + " puts " + ask.getSelectedCards().size() + " cards to Seclusion mat", 1);
		ask = new Ask();
		return ask;
	}
}
