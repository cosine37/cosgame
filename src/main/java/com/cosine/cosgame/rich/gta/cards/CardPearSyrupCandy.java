package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPearSyrupCandy extends Card {
	public CardPearSyrupCandy() {
		super();
		id = 10010;
		name = "梨膏糖";
		desc = "本回合获得精准效果。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.setBroadcastImg("card/"+id);
			
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，本回合获得精准效果。");
			board.getLogger().log(player.getName() + " 本回合获得精准效果");
			
			player.getBuff().setAimBoost(1);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
