package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardRelease extends Card {
	public CardRelease() {
		super();
		id = 10;
		name = "出狱卡";
		desc = "立即出狱，不需要支付保释费。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (player.isInJail()) {
			player.outOfJail();
			
			board.getLogger().log(player.getName() + " 被释放出狱");
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了出狱卡，被释放出狱。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() && player.getPhase() == Consts.PHASE_ROLL) {
			return true;
		}
		return false;
	}
}
