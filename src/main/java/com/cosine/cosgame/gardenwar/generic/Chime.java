package com.cosine.cosgame.gardenwar.generic;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Chime extends Card{
	public Chime() {
		name = "编钟";
		img = "chime";
		cost = 3;
		type = Consts.CARD;
		sun = 3;
		autoplay = true;
		addClan(Consts.ITEM);
	}
}
