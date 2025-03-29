package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardNugget extends Card {
	public CardNugget() {
		super();
		id = 16;
		name = "金珠";
		desc = "获得$5000。消耗。";
		rarity = 3;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int amount = 5000;
			player.addMoney(amount);
			
			board.getLogger().log(player.getName() + " 获得了 $"+amount);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了金珠，获得了$" + amount + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
