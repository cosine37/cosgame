package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CitizenSquare extends Card{
	public CitizenSquare() {
		super();
		name = "市民广场";
		cost = 5;
		img = "p516";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public int getExtraScore() {
		int ans = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			if (player.getBuilt().get(i).getColor() == CitadelsConsts.YELLOW
					|| player.getBuilt().get(i).getColor() == CitadelsConsts.GREENYELLOW
					|| player.getBuilt().get(i).getColor() == CitadelsConsts.REDYELLOW
					|| player.getBuilt().get(i).getColor() == CitadelsConsts.BLUEYELLOW) {
				ans++;
			}
		}
		return ans;
	}
}
