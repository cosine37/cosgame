package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardGift extends Card {
	public CardGift() {
		super();
		id = 9001;
		name = "礼物";
		desc = "随机获得1张牌。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			
			
			boolean f;
			int x = 0;
			f = player.addRandomCard();
			if (f) x++;
			
			board.setBroadcastImg("card/"+id);
			board.getLogger().logGainCard(player, x);
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了礼物，但是手牌已达上限，没有获得任何牌。");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了礼物，随机获得了" + x + "张牌。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
