package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Cactus extends Card{
	public Cactus() {
		name = "仙人掌";
		img = "cactus";
		cost = 3;
		atk = 1;
		type = Consts.CARD;
		addClan(Consts.WOOD);
		desc = "抽1张牌。";
	}
	
	public void play() {
		super.play();
		player.draw(1);
	}
}
