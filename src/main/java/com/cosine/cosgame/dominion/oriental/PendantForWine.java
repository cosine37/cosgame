package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class PendantForWine extends Card{
	public PendantForWine() {
		super();
		this.name = "PendantForWine";
		this.image = "/image/Dominion/cards/Oriental/PendantForWine.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		
		this.safe = false;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size()>0) {
			player.addCoin(5);
			if (player.getHand().size()<5) {
				log(player.getName() + " discards hand for +$5", 1);
				player.discardHand();
			} else {
				ask.setType(Ask.HANDCHOOSE);
				ask.setUpper(4);
				ask.setLower(4);
				ask.setMsg("Discard 4 cards");
			}
			
			
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		log(player.getName()+" discards 4 cards for +$5",1);
		for (int i=0;i<ask.getSelectedCards().size();i++) {
			player.discard(ask.getSelectedCards().get(i));
		}
		ask = new Ask();
		return ask;
		
	}
}
