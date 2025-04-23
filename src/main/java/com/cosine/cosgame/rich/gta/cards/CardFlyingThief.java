package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardFlyingThief extends Card {
	public CardFlyingThief() {
		super();
		id = 57;
		name = "飞贼卡";
		desc = "成功率70%；+1starP，从指定的玩家处偷取一张手牌，消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		aim = 70;
	}
	
	public void play(int rawOptions) {
		boolean f = aimed();
		
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			board.setBroadcastImg("card/"+id);
			
			if (f) {
				Card c = tp.sendRandom();
				if (c == null) {
					board.getLogger().log("但是 " + player.getName() + " 没有从 " + tp.getName() + " 处偷到任何牌");
					board.setBroadcastMsg(player.getName() + "打出了飞贼卡，但是 " + player.getName() + "没有从" + targetName + "处偷到任何牌，却依然增加了1点通缉值，这就尴尬了。");
				} else {
					board.getLogger().log(player.getName() + " 从 " + tp.getName() + " 处偷取了一张 " + c.getName());
					board.setBroadcastMsg(player.getName() + "打出了飞贼卡，从" + targetName + "处偷取了一张" + c.getName() + "，" + player.getName() + "增加了1点通缉值");
					
					player.getHand().add(c);
				}
				
				board.getLogger().logGainstar(player, 1);
				
				player.addStar(1);
				
			} else {
				board.getLogger().log("但是没有命中 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了飞贼卡，但是并没有命中" + targetName +"。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
