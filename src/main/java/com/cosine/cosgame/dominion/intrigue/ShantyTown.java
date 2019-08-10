package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class ShantyTown extends Card{
	public ShantyTown() {
		super();
		this.name = "ShantyTown";
		this.image = "/image/Dominion/cards/Intrigue/ShantyTown.png";
		this.types[INDEX_ACTION] = true;
		this.action = 2;
		this.price = 3;
	}
	
	public Ask play() {
		vanilla();
		boolean flag = true;
		for (int i=0;i<player.getHand().size();i++) {
			if (player.getHand().get(i).isActionType()) {
				flag = false;
				break;
			}
		}
		if (flag) {
			player.draw(2);
		}
		Ask ask = new Ask();
		return ask;
	}
}
