package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CRHStation extends Card {
	public CRHStation() {
		super();
		name = "高铁站";
		cost = 5;
		img = "p504";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getExtraScore() {
		int ans = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			if (player.getBuilt().get(i).getColor() == CitadelsConsts.PURPLE) {
				ans++;
			}
		}
		ans = ans-1;
		return ans;
	}
}
