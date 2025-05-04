package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardYoung extends Card {
	public CardYoung() {
		super();
		id = 73;
		name = "连清卡";
		desc = "回合结束时，若你只有1张手牌，获得1张随机牌。住院时消耗该牌。";
		rarity = 1;
		passive();
	}
	
	public boolean exhaustOnWard() {return true;}
	
	public void onTurnEnd() {
		if (player.getHand().size() == 1) {
			boolean f = player.addRandomCard();
			if (f) {
				board.getLogger().log("因 " + name + " 的效果， " + player.getName() + " 获得了 1 张随机牌。");
			}
		}
		
	}
	
}
