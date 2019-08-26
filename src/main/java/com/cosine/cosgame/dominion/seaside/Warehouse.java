package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Warehouse extends Card{
	public Warehouse() {
		super();
		this.name = "Warehouse";
		this.image = "/image/Dominion/cards/Seaside/Warehouse.png";
		this.types[INDEX_ACTION] = true;
		this.price = 3;
		this.card = 3;
		this.action = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() <= 3) {
			int n = player.getHand().size();
			player.discardHand();
			log(player.getName()+" discards " + n +" cards",1);
		} else {
			ask.setType(Ask.HANDCHOOSE);
			ask.setMsg("Discards 3 cards");
			ask.setUpper(3);
			ask.setLower(3);
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		log(player.getName()+" discards 3 cards",1);
		for (int i=0;i<ask.getSelectedCards().size();i++) {
			player.discard(ask.getSelectedCards().get(i));
		}
		ask = new Ask();
		return ask;
	}
	
}
