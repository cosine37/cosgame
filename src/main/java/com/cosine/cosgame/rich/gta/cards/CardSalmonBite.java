package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardSalmonBite extends Card {
	public CardSalmonBite() {
		super();
		id = 20001;
		name = "烟熏三文鱼";
		desc = "回复1点生命值。消耗。";
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			player.addHp(1);
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了烟熏三文鱼，回复1点生命值。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
