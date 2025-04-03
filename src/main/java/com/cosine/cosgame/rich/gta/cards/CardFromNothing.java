package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardFromNothing extends Card {
	public CardFromNothing() {
		super();
		id = 20;
		name = "无中生有";
		desc = "随机获得2张牌。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			
			
			boolean f;
			int x = 0;
			f = player.addRandomCard();
			if (f) x++;
			f = player.addRandomCard();
			if (f) x++;
			
			board.setBroadcastImg("card/"+id);
			board.getLogger().logGainCard(player, x);
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了无中生有，但是手牌已达上限，没有获得任何牌。");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了无中生有，随机获得了" + x + "张牌。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
