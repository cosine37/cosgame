package com.cosine.cosgame.dominion.intrigue;

import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Harem extends Card{
	public Harem() {
		super();
		this.name = "Harem";
		this.image = "/image/Dominion/cards/Intrigue/Harem.png";
		this.types[INDEX_TREASURE] = true;
		this.types[INDEX_VICTORY] = true;
		this.score = 2;
		this.coin = 2;
		this.price = 6;
	}
	
}
