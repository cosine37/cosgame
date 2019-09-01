package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Bazaar extends Card{
	public Bazaar() {
		super();
		this.name = "Bazaar";
		this.image = "/image/Dominion/cards/Seaside/Bazaar.png";
		this.types[INDEX_ACTION] = true;
		this.card = 1;
		this.action = 2;
		this.coin = 1;
		this.price = 5;
	}
	
}
