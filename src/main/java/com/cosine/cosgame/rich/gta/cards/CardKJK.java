package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardKJK extends Card {
	public CardKJK() {
		super();
		id = 9005;
		name = "锟斤拷";
		desc = "因为牌名非法，丢弃则+1starP。";
		rarity = 0;
		curse();
	}
	
	public void play(int rawOptions) {
		
	}
	
	public void onThrow() {
		player.addStar(1);
		board.getLogger().log(player.getName() + " 增加了 1 点通缉值");
		
		board.setBroadcastImg("card/"+id);
		board.setBroadcastMsg(player.getName() + "丢弃了" + name + "，增加了1点通缉值。");
	}
	
	public boolean playable() {
		return false;
	}
}
