package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class GreatA3Factory extends Card{
	public GreatA3Factory() {
		super();
		name = "大成三厂";
		cost = 6;
		img = "p610";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void alterPlayerAbility() {
		if (board.getRoundCount() > builtRound) {
			int x = player.getBuildLimit()+1;
			player.setBuildLimit(x);
		}
	}
}
