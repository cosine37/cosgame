package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Sunflower extends Card{
	public Sunflower() {
		name = "向日葵";
		img = "sunflower";
		cost = 3;
		type = Consts.CARD;
		sun = 2;
		autoplay = true;
		addClan(Consts.FLOWER);
		
	}
}
