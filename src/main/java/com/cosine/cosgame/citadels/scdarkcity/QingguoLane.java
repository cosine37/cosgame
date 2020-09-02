package com.cosine.cosgame.citadels.scdarkcity;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class QingguoLane extends Card {
	public QingguoLane() {
		super();
		name = "青果巷";
		cost = 4;
		img = "p402";
		color = CitadelsConsts.PURPLE;
	}
	
	public void endTurnEffect() {
		if (player.getCoin() == 0) {
			board.log(player.getName() + "发动青果巷的效果，回合结束无￥时白嫖1￥。");
			player.addCoin(1);
		}
		
	}
}
