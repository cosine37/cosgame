package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class MagnetShroom extends Card{
	public MagnetShroom() {
		name = "磁力菇";
		img = "magnetShroom";
		cost = 5;
		level = 1;
		type = Consts.CARD;
		addClan(Consts.MUSHROOM);
		desc = "抽2张牌。";
	}
	
	public void play() {
		super.play();
		player.draw(2);
	}
}
