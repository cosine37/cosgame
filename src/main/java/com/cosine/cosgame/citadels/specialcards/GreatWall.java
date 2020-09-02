package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class GreatWall extends Card{
	public GreatWall() {
		super();
		name = "明城墙";
		cost = 6;
		img = "p605";
		color = CitadelsConsts.PURPLE;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		for (int i=0;i<player.getBuilt().size();i++) {
			if (player.getBuilt().get(i).getImg().contentEquals(img)) {
				player.setGreatWallIndex(i);
			}
		}
	}
}
