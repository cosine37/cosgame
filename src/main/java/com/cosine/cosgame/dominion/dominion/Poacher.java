package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Poacher extends Card{
	public Poacher() {
		super();
		this.name = "Poacher";
		this.image = "/image/Dominion/cards/Dominion/Poacher.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.card = 1;
		this.action = 1;
		this.coin = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		int emptyPile = board.getNumEmptyPile();
		if (emptyPile == 0) {
			return ask;
		}
		if (emptyPile >= player.getHand().size()) {
			player.discardHand();
			return ask;
		}
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(emptyPile);
		ask.setLower(emptyPile);
		if (emptyPile == 1) {
			ask.setMsg("Discard a card");
		} else if (emptyPile == 2) {
			ask.setMsg("Discard two cards");
		} else if (emptyPile == 3) {
			ask.setMsg("Discard three cards");
		} else if (emptyPile == 4) {
			ask.setMsg("Discard four cards");
		} else {
			ask.setMsg("Discard " + Integer.toString(emptyPile) + " cards");
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		int i,j;
		int n = ask.getSelectedCards().size();
		if (n == 1) {
			log(player.getName() + " discards a card", 1);
		} else {
			log(player.getName() + " discards " + n + " cards", 1);
		}
		for (i=0;i<ask.getSelectedCards().size();i++) {
			for (j=player.getHand().size()-1;j>=0;j--) {
				if (player.getHand().get(j).getName().equals(ask.getSelectedCards().get(i))) {
					player.discard(j);
					break;
				}
			}
		}
		ask = new Ask();
		return ask;
	}
}
