package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class PeaShooter extends Card{
	public PeaShooter() {
		name = "豌豆射手";
		img = "peaShooter";
		cost = 2;
		type = Consts.CARD;
		atk = 2;
		autoplay = true;
		addClan(Consts.PEA);
	}
}
