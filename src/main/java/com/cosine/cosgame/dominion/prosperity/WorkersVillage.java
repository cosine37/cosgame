package com.cosine.cosgame.dominion.prosperity;

import com.cosine.cosgame.dominion.Card;

public class WorkersVillage extends Card{
	public WorkersVillage() {
		super();
		this.name = "Worker's Village";
		this.image = "/image/Dominion/cards/Prosperity/WorkersVillage.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.card = 1;
		this.action = 2;
		this.buy = 1;
	}
}
