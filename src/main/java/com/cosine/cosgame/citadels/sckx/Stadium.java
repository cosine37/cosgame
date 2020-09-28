package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Stadium extends Card{
	public Stadium() {
		super();
		name = "奥体中心";
		cost = 4;
		img = "p411";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		if (player.getNumBuilt() > 1) {
			int x = board.takeCoins(3);
			if (x>0) {
				this.beautifyLevel = x;
				board.log(player.getName() + "用" + Integer.toString(x) + "￥装饰了 奥体中心。");
			}
		}
	}
}
