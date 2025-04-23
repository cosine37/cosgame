package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardFakeCry extends Card {
	public CardFakeCry() {
		super();
		id = 54;
		name = "骗捐卡";
		desc = "+1starP，从指定的玩家处偷取$100，消耗。";
		rarity =0;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			board.setBroadcastImg("card/"+id);
			
			int amount = 100;
			
			board.getLogger().log(player.getName() + " 通过哭穷，获得了 " + tp.getName() + " 施舍的 $" + amount);
			board.setBroadcastMsg(player.getName() + "打出了骗捐卡，通过哭穷，获得了" + targetName + "施舍的$" + amount + "，" + player.getName() + "增加了1点通缉值");
			tp.loseMoney(amount);
			player.addMoney(amount);
			
			board.getLogger().logGainstar(player, 1);
			
			player.addStar(1);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
