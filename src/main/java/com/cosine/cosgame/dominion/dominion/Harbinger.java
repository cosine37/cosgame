package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Harbinger extends Card{
	public Harbinger() {
		super();
		this.name = "Harbinger";
		this.image = "/image/Dominion/cards/Dominion/Harbinger.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
		this.card = 1;
		this.action = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getDiscard().size() == 0) {
			
		} else {
			List<String> revealed = new ArrayList<String>();
			List<String> revealedImage = new ArrayList<String>();
			for (int i=0;i<player.getDiscard().size();i++) {
				revealed.add(player.getDiscard().get(i).getName());
				revealedImage.add(player.getDiscard().get(i).getImage());
			}
			ask.setViewedCards(revealed);
			ask.setViewedCardsImage(revealedImage);
			ask.setType(Ask.VIEW);
			ask.setSubType(Ask.CHOOSE);
			ask.setMsg("You may top deck a card from discard");
			ask.setUpper(1);
			ask.setLower(0);
			ask.setViewedCards(revealed);
			ask.setViewedCardsImage(revealedImage);
			ask.setResLevel(0);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		
		if (ask.getSelectedRevealed().size()==0) {
			
		} else if (ask.getSelectedRevealed().size()==1) {
			int index = ask.getSelectedRevealed().get(0);
			Card card = player.getDiscard().remove(index);
			player.topDeck(card);
			log(player.getName() + " topdecks a card from dicard", 1);
		} else {
			
		}
		ask = new Ask();
		return ask;
	}
}
