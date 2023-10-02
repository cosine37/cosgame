package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class FumeShroom extends Card{
	public FumeShroom() {
		name = "大喷菇";
		img = "fumeShroom";
		cost = 3;
		atk = 1;
		type = Consts.CARD;
		addClan(Consts.MUSHROOM);
		desc = "出牌区每有2张蘑菇（包括这张）额外获得p1。";
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
		int n = numMushroom / 2;
		player.addAtk(n);
	}
}
