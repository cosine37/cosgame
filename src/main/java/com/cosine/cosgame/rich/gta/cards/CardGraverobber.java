package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardGraverobber extends Card {
	public CardGraverobber() {
		super();
		id = 62;
		name = "盗墓卡";
		desc = "成功率：80%；+2starP，随机获得2张牌。消耗。";
		rarity = 0;
		aim = 80;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			boolean a = aimed();
			
			if (a) {
				boolean f;
				int x = 0;
				f = player.addRandomCard();
				if (f) x++;
				f = player.addRandomCard();
				if (f) x++;
				
				board.setBroadcastImg("card/"+id);
				board.getLogger().logGainstar(player, 2);
				board.getLogger().logGainCard(player, x);
				if (x == 0) {
					board.setBroadcastMsg(player.getName() + "使用了" + name +"，增加1点通缉值，但是手牌已达上限，没有获得任何牌。");
				} else {
					board.setBroadcastMsg(player.getName() + "使用了" + name +"，增加1点通缉值，随机获得了" + x + "张牌。");
				}
				
				player.addStar(1);
			} else {
				board.getLogger().log("但是 " + player.getName() + " 什么也没挖到，这就尴尬了");
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是什么也没挖到，这就尴尬了。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
