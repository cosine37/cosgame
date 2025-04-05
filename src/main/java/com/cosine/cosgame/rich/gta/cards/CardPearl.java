package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPearl extends Card {
	public CardPearl() {
		super();
		id = 15;
		name = "珍珠";
		desc = "回忆是珍珠，友情是钻石！获得$2000。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int amount = 2000;
			player.addMoney(amount);
			
			board.getLogger().log(player.getName() + " 获得了 $"+amount);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name +"，获得了$" + amount + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
