package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class Card1 extends Card {
	public Card1() {
		super();
		id = 1;
		name = "一点卡";
		desc = "你下一次掷骰子的点数为1。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int result = 1;
			player.getBuff().setNextRoll(result);
			
			board.getLogger().logSetDiceResult(player, result);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name +"，下一次掷骰子的点数为" + result + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
