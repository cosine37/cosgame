package com.cosine.cosgame.gardenwar.basic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class WineCup extends Card{
	public WineCup() {
		name = "爵";
		img = "wineCup";
		cost = 0;
		type = Consts.CARD;
		sun = 2;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
