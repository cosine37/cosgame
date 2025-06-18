package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardFriedRice extends Card {
	public CardFriedRice() {
		super();
		id = 10012;
		name = "扬州炒饭";
		desc = "+2healthP。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			player.addHp(2);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，回复2点生命值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
