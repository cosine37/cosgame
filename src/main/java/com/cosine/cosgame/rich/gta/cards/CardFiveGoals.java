package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardFiveGoals extends Card {
	public CardFiveGoals() {
		super();
		id = 95;
		name = "九五至尊";
		desc = "获得随机牌，直至你有5张手牌。消耗。";
		rarity = 3;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int x = 0;
			while (player.getHand().size()<5) {
				player.addRandomCard();
				x++;
			}
			
			board.setBroadcastImg("card/"+id);
			
			if (x == 0) {
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是没有获得任何牌，这就尴尬了。");
				board.getLogger().log(player.getName() + " 使用了 " + name + " ，但是没有获得任何牌，这就尴尬了");
			} else {
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，随机获得了" + x + "张牌。");
				board.getLogger().logGainCard(player, x);
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
