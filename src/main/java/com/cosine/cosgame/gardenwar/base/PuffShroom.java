package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class PuffShroom extends Card{
	public PuffShroom() {
		name = "小喷菇";
		img = "puffShroom";
		cost = 0;
		type = Consts.CARD;
		atk = 1;
		autoplay = true;
		addClan(Consts.MUSHROOM);
	}
}
