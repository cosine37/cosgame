package com.cosine.cosgame.rich.gta.cards;

import java.util.ArrayList;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Vehicle;

public class CardArsenal extends Card {
	public CardArsenal() {
		super();
		id = 64;
		name = "军械库";
		desc = "丢弃所有手牌，若丢弃的牌不小于1张，随机获4张牌。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int i;
			for (i=0;i<player.getHand().size();i++) {
				player.getHand().get(i).onThrow();
			}
			
			int n = player.getHand().size();
			
			if (player.getHand().size()>0) {
				board.getLogger().log(player.getName() + " 弃置了 " + n + " 张手牌并随机获得了 "+ 4 + " 张牌");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，弃置了" + n + "张手牌并随机获得了" + 4 + "张牌。");
				player.setHand(new ArrayList<>());
				for (i=0;i<4;i++) {
					player.addRandomCard();
				}
			} else {
				board.getLogger().log(player.getName() + " 没有丢弃任何手牌，所以没有获得任何牌，这就尴尬了");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，但是没有丢弃任何手牌，所以没有获得任何牌，这就尴尬了。");
			}
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
