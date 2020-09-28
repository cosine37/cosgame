package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class DinoWaterTown extends Card{
	public DinoWaterTown() {
		super();
		name = "迪诺水镇";
		cost = 7;
		img = "p701";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public int getBuildCost() {
		int ans = cost;
		for (int i=0;i<player.getBuilt().size();i++) {
			Card c = player.getBuilt().get(i);
			if (c.getColor() == CitadelsConsts.PURPLE
					|| c.getColor() == CitadelsConsts.GREENPURPLE) {
				ans = ans-2;
			}
		}
		if (ans<1) ans = 1;
		return ans;
	}
}
