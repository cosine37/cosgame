package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardMacRetro extends Card {
	public CardMacRetro() {
		super();
		id = 20007;
		name = "魅可哑光";
		desc = "-2starP。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.getLogger().logLoseStar(player, 2);
			player.loseStar(2);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了魅可哑光，减少2点通缉值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
