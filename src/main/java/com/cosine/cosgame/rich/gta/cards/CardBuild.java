package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardBuild extends Card {
	public CardBuild() {
		super();
		id = 41;
		name = "加盖卡";
		desc = "若当前所在地产已被购买，将当前所在地产升一级。消耗。";
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		boolean f = changeEstateLevel(player.getPlace(),1);
		if (f) {
			board.getLogger().log(player.getName() + " 升级了 " + player.getPlace().getName());
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "升级了" + player.getPlace().getName() + "。");
		} else {
			if (player.getPlace() != null) {
				board.getLogger().log(player.getName() + " 无法升级 " + player.getPlace().getName());
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法升级" + player.getPlace().getName() + "，这就尴尬了。");
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
