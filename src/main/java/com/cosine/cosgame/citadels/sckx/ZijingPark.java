package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class ZijingPark extends Card{
	public ZijingPark() {
		super();
		name = "紫荆公园";
		cost = 4;
		img = "p405";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		int x = board.getPlayerIndex(player);
		board.moveCrownTo(x);
	}
}
