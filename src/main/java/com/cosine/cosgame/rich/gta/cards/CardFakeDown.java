package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardFakeDown extends Card {
	public CardFakeDown() {
		super();
		id = 36;
		name = "碰瓷卡";
		desc = "-1healthP，指定一名玩家获得+2starP。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			
			
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			board.getLogger().logLoseHp(player, 1);
			board.getLogger().log(player.getName() + " 指控 " + targetName + " 撞倒了自己， " + tp.getName() + " 的通缉值增加了2");
			board.setBroadcastMsg(player.getName() + "使用碰瓷卡，失去了1点生命值，并指控" + targetName + "撞倒了自己，" + tp.getName() + "的通缉值增加了2。");
			
			player.loseHp(1);
			tp.addStar(2);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
