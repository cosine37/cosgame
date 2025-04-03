package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardRumor extends Card {
	public CardRumor() {
		super();
		id = 21;
		name = "谣言卡";
		desc = "成功率：70%；指定一名玩家获得+1starP。消耗。";
		rarity = 0;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		aim = 70;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			boolean f = aimed();
			
			if (f) {
				tp.addStar(1);
				
				board.getLogger().log(player.getName() + " 对 " + targetName + " 制造了谣言， " + tp.getName() + "的通缉值增加了1");
				board.setBroadcastMsg(player.getName() + "使用了谣言卡，对" + targetName + "制造了谣言，" + tp.getName() + "的通缉值增加了1。");
			} else {
				board.getLogger().log(player.getName() + " 对 " + targetName + " 制造了谣言，但是并没有影响到 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了谣言卡，对" + targetName + "制造了谣言，但是并没有影响到" + targetName);
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
