package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardCP3 extends Card {
	public CardCP3() {
		super();
		id = 58;
		name = "5 8 卡";
		desc = "偷取一名玩家一张手牌，消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 2;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			Card c = tp.sendRandom();
			if (c == null) {
				board.getLogger().log(player.getName() + " 召唤了五八，但是没有从 " + tp.getName() + " 处偷到任何牌");
				board.setBroadcastMsg(player.getName() + "召唤了58，但是没有从" + targetName + "处偷到任何牌，这就尴尬了。");
			} else {
				board.getLogger().log(player.getName() + " 召唤的五八从 " + tp.getName() + " 处偷取了一张 " + c.getName());
				board.setBroadcastMsg(player.getName() + "召唤的58从" + targetName + "处偷取了一张" + c.getName() + "并一个妙传递给了" + player.getName() + "，58真是脏的飞起！");
			}
			
			player.getHand().add(c);
			
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
