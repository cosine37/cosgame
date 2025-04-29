package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardBlessings extends Card {
	public CardBlessings() {
		super();
		id = 67;
		name = "逢凶化吉";
		desc = "获得等同于已损失生命值数量的随机牌。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int x = 0;
			int i;
			int n = player.getMaxHp() - player.getHp();
			for (i=0;i<n;i++) {
				boolean f = player.addRandomCard();
				if (f) x++;
			}
			
			board.setBroadcastImg("card/"+id);
			
			if (n <= 0) {
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是" + player.getName() + "的生命值已满，没有获得任何牌，这就尴尬了。");
				board.getLogger().log(player.getName() + " 使用了 " + name + " ，但是" + player.getName() + "的生命值已满，没有获得任何牌，这就尴尬了");
			} else if (x == n){
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，随机获得了等同于已损失生命值数量的" + x + "张牌。");
				board.getLogger().logGainCard(player, x);
			} else {
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，随机获得了小于已损失生命值数量的" + x + "张牌。");
				board.getLogger().logGainCard(player, x);
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
