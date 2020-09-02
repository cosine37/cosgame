package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class DrownedCity extends Card {
	public DrownedCity() {
		super();
		name = "淹城遗址";
		cost = 5;
		img = "p505";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getExtraScore() {
		int ans = player.getHand().size();
		return ans;
	}
}
