package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardPanda extends Card {
	public CardPanda() {
		super();
		id = 9003;
		name = "国宝";
		desc = "一个自称不是马后炮电台的专家的人塞给你的，丢弃则+3starP。";
		rarity = 3;
	}
	
	public void play(int rawOptions) {
		
	}
	
	public void onThrow() {
		player.addStar(3);
		board.getLogger().log(player.getName() + " 增加了 3 点通缉值");
		
		board.setBroadcastImg("card/"+id);
		board.setBroadcastMsg(player.getName() + "丢弃了" + name + "，增加了3点通缉值。");
	}
	
	public boolean playable() {
		return false;
	}
}
