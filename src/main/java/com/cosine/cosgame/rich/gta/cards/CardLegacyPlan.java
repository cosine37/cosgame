package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardLegacyPlan extends Card {
	public CardLegacyPlan() {
		super();
		id = 74;
		name = "遗计卡";
		desc = "当你失去healthP时，获得2张随机牌。住院时消耗该牌。";
		rarity = 2;
		passive();
	}
	
	public boolean exhaustOnWard() {return true;}
	
	public void onLoseHp(int x) {
		boolean f;
		int y = 0;
		f = player.addRandomCard();
		if (f) y++;
		f = player.addRandomCard();
		if (f) y++;
		if (y>0) board.getLogger().log("因 " + name + " 的效果， " + player.getName() + " 获得了 " + y + " 张随机牌。");
	}
	
}
