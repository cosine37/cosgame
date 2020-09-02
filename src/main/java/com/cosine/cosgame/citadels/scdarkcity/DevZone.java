package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class DevZone extends Card {
	public DevZone() {
		super();
		name = "开发区";
		cost = 5;
		img = "p506";
		color = CitadelsConsts.PURPLE;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getIdenticalLimit();
		x = x+1;
		player.setIdenticalLimit(x);
	}

}
