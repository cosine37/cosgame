package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class NearGarden extends Card{
	public NearGarden() {
		super();
		name = "近园";
		cost = 2;
		img = "p205";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		boolean flag = false;
		int i;
		for (i=0;i<player.getBuilt().size();i++) {
			Card c = player.getBuilt().get(i);
			if (c.getName().contentEquals(name)) {
				continue;
			} else {
				if (c.getColor() == CitadelsConsts.PURPLE || c.getColor() == CitadelsConsts.GREENPURPLE) {
					flag = true;
					break;
				}
			}
		}
		if (flag) {
			int x = board.takeCoins(1);
			if (x>0) {
				beautify();
				board.log("因为" + player.getName() + "有特殊建筑，近园的装饰等级提升了。");
			}
		}
	}
}
