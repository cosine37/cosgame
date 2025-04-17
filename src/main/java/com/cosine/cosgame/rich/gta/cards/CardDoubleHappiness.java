package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardDoubleHappiness extends Card {
	public CardDoubleHappiness() {
		super();
		id = 10006;
		name = "红双喜";
		desc = "-1healthP，3回合内获得精准效果。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.setBroadcastImg("card/"+id);
			
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，失去了1点生命值且3回合内获得精准效果。");
			board.getLogger().logLoseHp(player, 1);
			board.getLogger().log(player.getName() + " 3回合内获得精准效果");
			
			player.loseHp(1);
			player.getBuff().setAimBoost(3);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
