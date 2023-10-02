package com.cosine.cosgame.gardenwar.generic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class DaggerAxe extends Card{
	public DaggerAxe() {
		name = "戈";
		img = "daggerAxe";
		cost = 1;
		type = Consts.CARD;
		atk = 3;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
