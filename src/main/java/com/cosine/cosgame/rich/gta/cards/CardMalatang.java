package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardMalatang extends Card {
	public CardMalatang() {
		super();
		id = 86;
		name = "麻辣烫";
		desc = "+3healthP，每有一个空手牌位，获得一张烫烫烫。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			player.addHp(3);
			
			board.getLogger().log(player.getName() + "每有一个空手牌位，获得一张烫烫烫。");
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，回复3点生命值，且每有一个空手牌位，获得一张烫烫烫。");
			
			while (player.fullHand() == false) {
				player.addCard(new CardTTT());
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
