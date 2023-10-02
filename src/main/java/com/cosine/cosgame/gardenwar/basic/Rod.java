package com.cosine.cosgame.gardenwar.basic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Rod extends Card{
	public Rod() {
		name = "木棍";
		img = "rod";
		cost = 0;
		type = Consts.CARD;
		atk = 1;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
