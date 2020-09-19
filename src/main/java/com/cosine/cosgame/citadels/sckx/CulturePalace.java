package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CulturePalace extends Card{
	public CulturePalace() {
		super();
		name = "文化宫";
		cost = 6;
		img = "p609";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void afterTakeActionEffect() {
		if (player.getRole().getColor() == -1) {
			player.addCoin(1);
			board.log(player.getName() + "因为 文化宫 的效果额外获得了1￥。");
		}
	}
}
