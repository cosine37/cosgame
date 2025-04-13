package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardRefuseRent extends Card {
	public CardRefuseRent() {
		super();
		id = 23;
		name = "赖账卡";
		desc = "+2starP，2回合内无需支付房租，可以在掷骰之后移动之前使用，消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，2回合内无需支付房租。");
			board.getLogger().log(player.getName() + " 2回合内无需支付房租");
			
			player.addStar(2);
			
			player.getBuff().setFreeRound(2);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return alsoPlayableAfterRoll();
	}
}
