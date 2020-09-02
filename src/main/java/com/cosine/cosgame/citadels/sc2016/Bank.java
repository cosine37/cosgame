package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Bank extends Card{
	public Bank() {
		super();
		name = "银行";
		cost = 6;
		img = "p608";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getNumTakeCoin()+1;
		player.setNumTakeCoin(x);
	}
}
