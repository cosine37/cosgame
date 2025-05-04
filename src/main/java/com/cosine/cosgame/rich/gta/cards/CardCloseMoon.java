package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardCloseMoon extends Card {
	public CardCloseMoon() {
		super();
		id = 75;
		name = "闭月卡";
		desc = "回合结束时获得1张随机牌。住院时消耗该牌。";
		rarity = 3;
		passive();
	}
	
	public boolean exhaustOnWard() {return true;}
	
	public void onTurnEnd() {
		boolean f = player.addRandomCard();
		if (f) {
			board.getLogger().log("因 " + name + " 的效果， " + player.getName() + " 获得了 1 张随机牌。");
		}
	}
	
}
