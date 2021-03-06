package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Buff;
import com.cosine.cosgame.dominion.Card;

public class FieldReaper extends Card{
	public FieldReaper() {
		super();
		this.name = "Field Reaper";
		this.image = "/image/Dominion/cards/Oriental/FieldReaper.png";
		this.types[INDEX_ACTION] = true;
		this.buy = 1;
		this.coin = 2;
		this.price = 4;
	}
	
	public int inPlay() {
		return Buff.MEMORIALBUYVICTORY;
	}
}
