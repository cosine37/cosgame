package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class MtMao extends Card{
	public MtMao() {
		super();
		name = "茅山道场";
		cost = 5;
		img = "p508";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public int getExtraScore() {
		int ans = 0;
		int count = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			if (player.getBuilt().get(i).getColor() == CitadelsConsts.PURPLE) {
				count++;
			}
		}
		if (count > 1) {
			ans = 0;
		} else {
			ans = 5;
		}
		return ans;
	}
}
