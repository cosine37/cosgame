package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Canal extends Card {
	public Canal() {
		super();
		name = "运河";
		cost = 6;
		img = "p607";
		color = CitadelsConsts.PURPLE;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getCostReducers().get(CitadelsConsts.PURPLE);
		x = x+1;
		player.getCostReducers().set(CitadelsConsts.PURPLE, x);
	}

}
