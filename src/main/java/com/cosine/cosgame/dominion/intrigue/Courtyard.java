package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Courtyard extends Card{
	public Courtyard() {
		super();
		this.name = "Courtyard";
		this.image = "/image/Dominion/cards/Intrigue/Courtyard.png";
		this.types[INDEX_ACTION] = true;
		this.price = 2;
		this.card = 3;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(1);
		ask.setLower(1);
		ask.setMsg("Put a card on top of your deck");
		return ask;
		
	}
	
	public Ask response(Ask a) {
		player.setBoard(board);
		player.topDeck(a.getSelectedCards().get(0));
		log(player.getName() + " puts a card on top of the deck", 1);
		Ask ask = new Ask();
		return ask;
	}
}
