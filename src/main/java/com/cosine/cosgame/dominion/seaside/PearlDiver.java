package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class PearlDiver extends Card{
	public PearlDiver() {
		super();
		this.name = "Pearl Diver";
		this.image = "/image/Dominion/cards/Seaside/PearlDiver.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 1;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getDeck().size() == 0) {
			
		} else {
			ask.setType(Ask.VIEW);
			ask.setSubType(Ask.SINGLEOPTION);
			List<String> options = new ArrayList<String>();
			options.add("Topdeck bottom card");
			ask.setOptions(options);
			Card c = player.getDeck().get(player.getDeck().size() - 1);
			List<String> revealed = new ArrayList<String>();
			List<String> revealedImage = new ArrayList<String>();
			revealed.add(c.getName());
			revealedImage.add(c.getImage());
			ask.setViewedCards(revealed);
			ask.setViewedCardsImage(revealedImage);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			int x = player.getDeck().size() - 1;
			Card c = player.getDeck().remove(x);
			log(player.getName() + " topdecks the bottom card",1);
			player.topDeck(c);
		}
		ask = new Ask();
		return ask;
	}
	
}
