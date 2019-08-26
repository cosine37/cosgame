package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class PaperMaker extends Card{
	public PaperMaker() {
		super();
		this.name = "PaperMaker";
		this.image = "/image/Dominion/cards/Oriental/PaperMaker.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(1);
		ask.setLower(1);
		ask.setMsg("Trash a card");
		return ask;
		
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardname = a.getSelectedCards().get(0);
		CardFactory factory = new CardFactory();
		int price = factory.createCard(cardname).getPrice() + 1;
		player.setBoard(board);
		player.trash(a.getSelectedCards(), "hand");
		log(player.getName() + " trashes a "+cardname,1);
		if (price > 1) {
			player.addMemorial(1);
			log(player.getName() + " receives a Memorial token",1);
		}
		ask = new Ask();
		return ask;
	}
}
