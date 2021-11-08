package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class GloomShroom extends Card{
	public GloomShroom() {
		name = "忧郁菇";
		img = "gloomShroom";
		cost = 8;
		atk = 1;
		level = 2;
		type = Consts.CARD;
		addClan(Consts.MUSHROOM);
		desc = "出牌区每有1张蘑菇（包括这张）抽1张牌。";
	}
	
	public void play() {
		super.play();
		int numMushroom = 0;
		int i;
		for (i=0;i<player.getPlay().size();i++) {
			if (player.getPlay().get(i).isClan(Consts.MUSHROOM)) {
				numMushroom++;
			}
		}
		int n = numMushroom;
		player.draw(n);
	}
}
