package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Hotel extends Card {
	public Hotel() {
		super();
		name = "大酒店";
		cost = 6;
		img = "p611";
		color = CitadelsConsts.PURPLE;
		trackCrown = true;
	}
	
	public void crownMovement(){
		player.addCoin(1);
		board.log(player.getName() + "因为 大酒店 的效果获得1￥。");
	}
}
