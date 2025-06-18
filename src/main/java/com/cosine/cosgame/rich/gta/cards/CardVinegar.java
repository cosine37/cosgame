package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardVinegar extends Card {
	public CardVinegar() {
		super();
		id = 10013;
		name = "镇江香醋";
		desc = "2回合内获得精准效果。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.setBroadcastImg("card/"+id);
			
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，2回合内获得精准效果。");
			board.getLogger().log(player.getName() + " 2回合内获得精准效果");
			
			player.getBuff().setAimBoost(2);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
