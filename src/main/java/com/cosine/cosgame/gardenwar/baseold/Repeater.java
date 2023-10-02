package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Repeater extends Card{
	public Repeater() {
		name = "双发豌豆";
		img = "repeater";
		level = 1;
		cost = 6;
		type = Consts.CARD;
		atk = 4;
		autoplay = true;
		addClan(Consts.PEA);
	}
}
