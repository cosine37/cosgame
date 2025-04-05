package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class Card2 extends Card {
	public Card2() {
		super();
		id = 2;
		name = "二点卡";
		desc = "你下一次掷骰子的点数为2。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int result = 2;
			player.getBuff().setNextRoll(result);
			
			board.getLogger().logSetDiceResult(player, result);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name +"，下一次掷骰子的点数为" + result + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
