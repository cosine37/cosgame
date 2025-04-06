package com.cosine.cosgame.rich.gta.cards;

import java.util.List;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardAllPoor extends Card {
	public CardAllPoor() {
		super();
		id = 49;
		name = "均贫卡";
		desc = "平分你和指定玩家的现金。消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			int newMoney = (player.getMoney() + tp.getMoney())/2;
			
			board.getLogger().log(player.getName() + " 和 " + targetName + " 平分了现金");
			board.setBroadcastMsg(player.getName() + "对" + targetName + "使用了均贫卡,平分了两人的现金。");
			
			tp.setMoney(newMoney);
			player.setMoney(newMoney);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
