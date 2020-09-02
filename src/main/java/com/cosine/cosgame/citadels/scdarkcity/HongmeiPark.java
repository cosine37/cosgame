package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class HongmeiPark extends Card {
	public HongmeiPark() {
		super();
		name = "红梅公园";
		cost = 6;
		img = "p606";
		color = CitadelsConsts.PURPLE;
	}
	
	public void endTurnEffect() {
		if (player.getHand().size() == 0) {
			board.log(player.getName() + "发动红梅公园的效果，回合结束无手牌时摸2张牌。");
			player.draw(2);
		}
		
	}
}
