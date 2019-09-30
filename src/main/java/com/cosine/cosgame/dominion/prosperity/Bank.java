package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Bank extends Card{
	public Bank() {
		super();
		this.name = "Bank";
		this.image = "/image/Dominion/cards/Prosperity/Bank.png";
		this.types[INDEX_TREASURE] = true;
		this.price = 7;
		this.autoplay = false;
	}
	
	public Ask play() {
		Ask ask = super.play();
		int t = 0;
		for (int i=0;i<player.getPlay().size();i++) {
			if (player.getPlay().get(i).isTreasure()) {
				t = t+1;
			}
		}
		player.addCoin(t);
		return ask;
	}
}
