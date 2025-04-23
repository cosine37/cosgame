package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardHarsh extends Card {
	public CardHarsh() {
		super();
		id = 56;
		name = "勒索卡";
		desc = "+2starP，从指定的玩家处偷取$800，消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			board.setBroadcastImg("card/"+id);
			
			int amount = 800;
			
			board.getLogger().log(player.getName() + " 放下狠话并逼迫 " + tp.getName() + " 交出 $" + amount);
			board.setBroadcastMsg(player.getName() + "打出了勒索卡，放下狠话并逼迫" + targetName + "交出$" + amount + "，" + player.getName() + "增加了2点通缉值");
			tp.loseMoney(amount);
			player.addMoney(amount);
			
			board.getLogger().logGainstar(player,2);
			
			player.addStar(2);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
