package com.cosine.cosgame.gardenwar.basic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Coin1 extends Card{
	public Coin1() {
		name = "布币";
		img = "coin1";
		cost = 0;
		type = Consts.CARD;
		sun = 1;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
