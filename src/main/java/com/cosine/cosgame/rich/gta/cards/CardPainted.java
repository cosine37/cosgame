package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPainted extends Card {
	public CardPainted() {
		super();
		id = 35;
		name = "彩绘卡";
		desc = "-4starP。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.getLogger().logLoseStar(player, 4);
			player.loseStar(4);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了彩绘卡，减少4点通缉值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
