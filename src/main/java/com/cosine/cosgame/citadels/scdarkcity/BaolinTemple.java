package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class BaolinTemple extends Card {
	public BaolinTemple() {
		super();
		name = "宝林寺";
		cost = 5;
		img = "p503";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getExtraScore() {
		int ans = player.getCoin();
		return ans;
	}
}
