package com.cosine.cosgame.citadels.sc2016;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Condo extends Card{
	public Condo() {
		super();
		name = "公寓";
		cost = 5;
		img = "p507";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public int getExtraScore() {
		int ans = 0;
		int i;
		List<Integer> count = new ArrayList<>();
		for (i=0;i<5;i++) {
			count.add(0);
		}
		for (i=0;i<player.getBuilt().size();i++) {
			int j = player.getBuilt().get(i).getColor();
			int t = count.get(j) + 1;
			count.set(j, t);
		}
		for (i=0;i<5;i++) {
			if (count.get(i)>=3) {
				ans = 3;
				break;
			}
		}
		return ans;
	}
}
