package com.cosine.cosgame.dominion.base;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Silver extends Card{
	public Silver() {
		super();
		this.name = "Silver";
		this.image = "/image/Dominion/cards/Base/Silver.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 2;
		this.price = 3;
	}
	
	public Ask play() {
		vanilla();
		Ask ask = new Ask();
		if (player.getPlayed(name) == 0) {
			int numMerchant = player.getPlayed("Merchant");
			player.addCoin(numMerchant);
		}
		player.addPlayed(name);
		return ask;
	}
}
