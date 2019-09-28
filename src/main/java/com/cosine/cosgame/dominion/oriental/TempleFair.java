package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class TempleFair extends Card{
	public TempleFair() {
		super();
		this.name = "Temple Fair";
		this.image = "/image/Dominion/cards/Oriental/TempleFair.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.card = 1;
		this.action = 1;
		this.buy = 1;
		this.coin = 1;
	}
	
	public Ask onGain(Player p) {
		int n = p.getDiscard().size() - 1;
		if (p.getDiscard().get(n).getName().equals(name)) {
			Card card = p.getDiscard().remove(n);
			p.getSeclusion().add(card);
			log("Temple Fair is gained to " + p.getName() + "'s Seclusion mat", 0);
		}
		Ask ask = new Ask();
		return ask;
	}
}
