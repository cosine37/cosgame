package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardSilverLion extends Card {
	public CardSilverLion() {
		super();
		id = 71;
		name = "白银狮子";
		desc = "失去生命值时，最多只会失去1点。丢弃该牌时，+1healthP";
		rarity = 2;
		passive();
	}
	
	public boolean capHurt() {
		return true;
	}
	
	public void onThrow() {
		player.addHp(1);
		
		board.setBroadcastImg("card/"+id);
		board.setBroadcastMsg(player.getName() + "丢弃了" + name + "，回复了1点生命值。");
	}
	
}
