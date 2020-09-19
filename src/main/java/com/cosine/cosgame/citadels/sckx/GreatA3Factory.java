package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class GreatA3Factory extends Card{
	public GreatA3Factory() {
		super();
		name = "大成三厂";
		cost = 6;
		img = "p610";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		player.addCoin(2);
		board.log("因为 大成三厂 的效果，" + player.getName() + "获得了2￥。");
	}
	
	public void alterPlayerAbility() {
		int x = player.getBuildLimit()+1;
		player.setBuildLimit(x);
	}
}
