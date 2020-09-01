package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Library extends Card{
	public Library() {
		super();
		name = "图书馆";
		cost = 6;
		img = "p603";
		color = CitadelsConsts.PURPLE;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getNumChoose()+1;
		player.setNumChoose(x);
	}
}
