package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Militia extends Card{
	public Militia() {
		super();
		this.name = "Militia";
		this.image = "/image/Dominion/cards/Dominion/Militia.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_ATTACK] = true;
		this.coin = 2;
		this.price = 4;
	}
	
	public Ask attack() {
		Ask ask = super.attack();
		int discardCount = player.getHand().size() - 3;
		if (discardCount < 1) return ask;
		ask.setCardName(name);
		ask.setType(Ask.HANDCHOOSE);
		ask.setSubType(Ask.ATTACK);
		ask.setUpper(discardCount);
		ask.setLower(discardCount);
		return ask;
	}
	
	public Ask response(Ask a) {
		if (a.getSubType() == Ask.ATTACK) {
			for (int i=0; i<a.getSelectedCards().size();i++) {
				player.discard(a.getSelectedCards().get(i));
				
			}
			log(player.getName() + " discards " + a.getSelectedCards().size() + " cards", 1);
		}
		Ask ask = new Ask();
		return ask;
	}
	
}
