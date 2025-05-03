package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardTTT extends Card {
	public CardTTT() {
		super();
		id = 9004;
		name = "烫烫烫";
		desc = "丢弃则-1healthP。";
		rarity = 0;
		curse();
	}
	
	public void play(int rawOptions) {
		
	}
	
	public void onThrow() {
		player.loseHp(1);
		board.getLogger().logLoseHp(player, 1);
		
		board.setBroadcastImg("card/"+id);
		board.setBroadcastMsg(player.getName() + "丢弃了" + name + "，失去了1点生命值。");
	}
	
	public boolean playable() {
		return false;
	}
}
