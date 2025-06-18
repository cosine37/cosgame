package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPeach extends Card {
	public CardPeach() {
		super();
		id = 10011;
		name = "水蜜桃";
		desc = "+1healthP。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			player.addHp(1);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，回复1点生命值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
