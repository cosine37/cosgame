package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardDividend extends Card {
	public CardDividend() {
		super();
		id = 25;
		name = "返现卡";
		desc = "你每次支出都会返现2%（向下取整）";
		rarity = 1;
		passive();
	}
	
	public boolean playable() {
		return false;
	}
	
	public void onLoseMoney(int x) {
		int y = x*2/100;
		if (y>0) {
			board.getLogger().log(player.getName() + " 获得了返现 $" + y);
			player.addMoney(y);
		}
	}
}
