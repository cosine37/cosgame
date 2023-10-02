package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class GoldMagnet extends Card{
	public GoldMagnet() {
		name = "吸金花";
		img = "goldMagnet";
		sun = 2;
		cost = 7;
		level = 2;
		type = Consts.CARD;
		addClan(Consts.FLOWER);
		desc = "抽2张牌。";
	}
	
	public void play() {
		super.play();
		player.draw(2);
	}
}
