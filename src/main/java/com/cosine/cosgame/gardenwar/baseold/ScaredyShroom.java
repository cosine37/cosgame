package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class ScaredyShroom extends Card{
	public ScaredyShroom() {
		name = "胆小菇";
		img = "scaredyShroom";
		cost = 1;
		atk = 1;
		type = Consts.CARD;
		addClan(Consts.MUSHROOM);
		desc = "若你放置区有牌，抽1张牌。";
	}
	
	public void play() {
		super.play();
		if (player.getEquip().size()>0) {
			player.draw(1);
		}
	}
}
