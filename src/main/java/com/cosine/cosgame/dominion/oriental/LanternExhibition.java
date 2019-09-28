package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class LanternExhibition extends Card{
	public LanternExhibition() {
		super();
		this.name = "Lantern Exhibition";
		this.image = "/image/Dominion/cards/Oriental/LanternExhibition.png";
		this.types[INDEX_ACTION] = true;
		this.price = 6;
		this.card = 3;
		this.action = 1;

		this.specialCare = SC_CLEANUPTOSECLUSION;
	}
	
}
