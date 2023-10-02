package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class GatlingPea extends Card{
	public GatlingPea() {
		name = "加特林豌豆";
		img = "gatlingPea";
		cost = 8;
		atk = 4;
		level = 2;
		type = Consts.CARD;
		addClan(Consts.PEA);
		desc = "抽1张牌，出牌区每有2张豌豆（包括这张），额外抽1张牌。";
	}
	
	public void play() {
		super.play();
		int numPea = 0;
		int i;
		for (i=0;i<player.getPlay().size();i++) {
			if (player.getPlay().get(i).isClan(Consts.PEA)) {
				numPea++;
			}
		}
		int n = 1+numPea/2;
		player.draw(n);
	}
}
