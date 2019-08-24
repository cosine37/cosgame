package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class FishingVillage extends Card{
	public FishingVillage() {
		super();
		this.name = "FishingVillage";
		this.image = "/image/Dominion/cards/Seaside/FishingVillage.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 3;
		this.action = 2;
		this.coin = 1;
		this.nt = 1;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.addAction(1);
		player.addCoin(1);
		log(player.getName() + " gets +1 Action, + $1 (from Fishing Village)", 1);
		return ask;
	}
	
}
