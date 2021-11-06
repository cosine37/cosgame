package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class SunShroom extends Card{
	public SunShroom() {
		name = "阳光菇";
		img = "sunShroom";
		cost = 0;
		type = Consts.CARD;
		sun = 1;
		addClan(Consts.MUSHROOM);
	}
}
