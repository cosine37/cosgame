package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardStay extends Card {
	public CardStay() {
		super();
		id = 0;
		name = "停留卡";
		desc = "指定一名玩家，该玩家下一次掷骰子的点数为0。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 1;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			board.getLogger().log(player.getName() + " 令 " + tp.getName() + " 下次掷骰子的点数为 0");
			board.setBroadcastMsg(player.getName() + "对" + targetName + "使用了停留卡，令其下次掷骰子的点数为0。");
			tp.getBuff().setNextRoll(0);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
