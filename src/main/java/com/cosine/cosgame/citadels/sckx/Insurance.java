package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Insurance extends Card{
	public Insurance() {
		super();
		name = "保险大厦";
		cost = 6;
		img = "p614";
		color = CitadelsConsts.PURPLE;
		trackOtherAssassin = true;
		expansion = 3;
	}
	
	public void alterPlayerAbility() {
		player.setInsured(true);
	}
	
	public void insuredEffect() {
		player.addCoin(1);
		board.log(player.getName() + "从 保险大厦 获得保险金1￥。");
	}
}
