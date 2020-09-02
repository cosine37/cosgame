package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class TianningTemple extends Card{
	public TianningTemple() {
		super();
		name = "天宁寺";
		cost = 4;
		img = "p403";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public int getExtraScore() {
		int ans = 0;
		int i;
		for (i=0;i<player.getBuilt().size();i++) {
			int j = player.getBuilt().get(i).getCost();
			if (j%2 == 1) {
				ans++;
			}
		}
		return ans;
	}
}
