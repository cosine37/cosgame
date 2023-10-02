package com.cosine.cosgame.gardenwar.basic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class BronzeSword extends Card{
	public BronzeSword() {
		name = "青铜剑";
		img = "bronzeSword";
		cost = 0;
		type = Consts.CARD;
		atk = 2;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
