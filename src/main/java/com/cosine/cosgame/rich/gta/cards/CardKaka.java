package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardKaka extends Card {
	public CardKaka() {
		super();
		id = 60;
		name = "卡卡卡";
		desc = "随机获得3张牌。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			
			
			boolean f;
			int x = 0;
			f = player.addRandomCard();
			if (f) x++;
			f = player.addRandomCard();
			if (f) x++;
			f = player.addRandomCard();
			if (f) x++;
			
			board.setBroadcastImg("card/"+id);
			board.getLogger().logGainCard(player, x);
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了卡卡卡，但是手牌已达上限，没有获得任何牌。");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了卡卡卡，随机获得了" + x + "张牌。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
