package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Village extends Card{
	public Village() {
		super();
		name = "新村";
		cost = 4;
		img = "p410";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public int buildCount() {
		return 2;
	}
	
	public boolean canBuildWhen(int x) {
		if (x>=5) {
			return false;
		} else {
			return true;
		}
	}
}
