package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardBitterMeat extends Card {
	public CardBitterMeat() {
		super();
		id = 72;
		name = "苦肉卡";
		desc = "-1healthP，获得2张随机牌，将该牌返回手牌，住院时消耗该牌。";
		rarity = 3;
	}
	
	public boolean returnHand() {return true;}
	public boolean exhaustOnWard() {return true;}
	
	public void play(int rawOptions) {
		if (playable()) {
			boolean f;
			int x = 0;
			f = player.addRandomCard();
			if (f) x++;
			f = player.addRandomCard();
			if (f) x++;
			board.setBroadcastImg("card/"+id);
			board.getLogger().logLoseHp(player, 1);
			board.getLogger().logGainCard(player, x);
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了" + name +" 失去1点生命值，但是手牌已达上限，没有获得任何牌。");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了" + name +"，失去1点生命值并随机获得了" + x + "张牌。");
			}
			
			player.loseHp(1);
		}
		exhaust = false;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
