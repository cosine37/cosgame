package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Bridge extends Card{
	public Bridge() {
		super();
		this.name = "Bridge";
		this.image = "/image/Dominion/cards/Intrigue/Bridge.png";
		this.types[INDEX_ACTION] = true;
		this.buy = 1;
		this.coin = 1;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		player.addPriceReduce(1);
		return ask;
	}
	
	
}
