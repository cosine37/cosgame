package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Island extends Card{
	public Island() {
		super();
		this.name = "Island";
		this.image = "/image/Dominion/cards/Seaside/Island.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_VICTORY] = true;
		this.price = 4;
		this.score = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() == 0) {
			Card card = player.getPlay().remove(player.getPlay().size()-1);
			player.getIsland().add(card);
			log(player.getName() + " puts an Island on Island mat",1);
		} else {
			ask.setType(Ask.HANDCHOOSE);
			ask.setMsg("Put a card from your hand to your Island mat");
			ask.setUpper(1);
			ask.setLower(1);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String cardName = ask.getSelectedCards().get(0);
		Card card = player.getPlay().remove(player.getPlay().size()-1);
		player.getIsland().add(card);
		for (int i=player.getHand().size()-1;i>=0;i--) {
			if (player.getHand().get(i).getName().equals(cardName)) {
				Card c = player.getHand().remove(i);
				player.getIsland().add(c);
				break;
			}
		}
		log(player.getName() + " puts an Island and a "+cardName+" on Island mat",1);
		ask = new Ask();
		return ask;
	}
	
}
