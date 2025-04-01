package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPoutine extends Card {
	public CardPoutine() {
		super();
		id = 20002;
		name = "盖浇薯条";
		desc = "+2healthP。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			player.addHp(2);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了盖浇薯条，回复2点生命值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
