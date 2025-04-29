package com.cosine.cosgame.rich.gta.cards;

import java.util.ArrayList;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardZhiHeng extends Card {
	public CardZhiHeng() {
		super();
		id = 61;
		name = "且慢卡";
		desc = "丢弃所有手牌，获得等量的随机卡牌。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int i;
			for (i=0;i<player.getHand().size();i++) {
				player.getHand().get(i).onThrow();
			}
			
			int n = player.getHand().size();
			
			if (player.getHand().size()>0) {
				board.getLogger().log(player.getName() + " 弃置了 " + n + " 张手牌并随机获得了 "+ n + " 张牌");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，弃置了" + n + "张手牌并随机获得了" + n + "张牌。");
				player.setHand(new ArrayList<>());
				for (i=0;i<n;i++) {
					player.addRandomCard();
				}
			} else {
				board.getLogger().log(player.getName() + " 没有丢弃任何手牌，所以没有获得任何卡牌，这就尴尬了");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，但是没有丢弃任何手牌，所以没有获得任何卡牌，这就尴尬了。");
			}
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
