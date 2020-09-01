package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class PlanningHall extends Card{
	public PlanningHall() {
		super();
		name = "规划馆";
		cost = 4;
		img = "p401";
		color = CitadelsConsts.PURPLE;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getNumReveal()+1;
		player.setNumReveal(x);
	}
}
