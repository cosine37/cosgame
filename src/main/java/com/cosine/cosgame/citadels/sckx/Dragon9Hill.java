package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Dragon9Hill extends Card{
	public Dragon9Hill() {
		super();
		name = "九龙山";
		cost = 6;
		img = "p612";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public int getScore() {
		return 8;
	}
	
	public void onBuild() {
		player.addCoin(1);
		board.log("因为 九龙山 的效果，" + player.getName() + "获得了1￥。");
	}
	
	public int getSecretScore() {
		return -2;
	}
}
