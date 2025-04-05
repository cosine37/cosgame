package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardOccupy extends Card {
	public CardOccupy() {
		super();
		id = 38;
		name = "强占卡";
		desc = "将当前所在地产据为己有，若成功发动，+2starP。消耗。";
		rarity = 2;
	}
	
	public void play(int rawOptions) {
		boolean flag = false;
		flag = changeEstateOwner(player.getPlace(), player);
		if (flag) {
			
			board.getLogger().log(player.getName() + " 强占了 " + player.getPlace().getName() + " 并获得了 2 点通缉值");
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，强占了" + player.getPlace().getName() + "并获得了2点通缉值。");
			
			player.addStar(2);
			
		} else {
			if (player.getPlace() != null) {
				board.getLogger().log(player.getName() + " 无法强占 " + player.getPlace().getName());
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法强占" + player.getPlace().getName() + "，这就尴尬了。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
