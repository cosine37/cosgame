package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class TwinSunflower extends Card{
	public TwinSunflower() {
		name = "双子向日葵";
		img = "twinSunflower";
		level = 2;
		cost = 7;
		type = Consts.CARD;
		sun = 4;
		autoplay = true;
		addClan(Consts.FLOWER);
	}
}
