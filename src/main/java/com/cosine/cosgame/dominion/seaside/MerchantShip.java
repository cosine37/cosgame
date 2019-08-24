package com.cosine.cosgame.dominion.seaside;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class MerchantShip extends Card{
	public MerchantShip() {
		super();
		this.name = "MerchantShip";
		this.image = "/image/Dominion/cards/Seaside/MerchantShip.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_DURATION] = true;
		this.price = 5;
		this.coin = 2;
		this.nt = 1;
	}
	
	public Ask duration() {
		Ask ask = super.duration();
		player.addCoin(2);
		log(player.getName() + " gets + $2 (from Merchant Ship)", 1);
		return ask;
	}
	
}
