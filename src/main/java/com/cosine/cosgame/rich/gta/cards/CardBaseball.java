package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardBaseball extends Card {
	public CardBaseball() {
		super();
		id = 17;
		name = "棒球卡";
		desc = "成功率：70%；+1starP，对一名玩家造成1点伤害。消耗。";
		rarity = 0;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 1;
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
				int x = getFinalAttack();

				board.getLogger().log(player.getName() + " 对 " + tp.getName() + " 造成了 " + x + " 点伤害");
				board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
				board.setBroadcastMsg(player.getName() + "使用了棒球卡，对" + targetName + "击出棒球，" + tp.getName() + "受到了" + x + "点伤害，" + player.getName() + "获得了1点通缉值。");
				
				player.hurt(tp, x);
				player.addStar(1);
			} else {
				board.getLogger().log(player.getName() + " 但是没有命中 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了棒球卡，对" + targetName + "击出棒球，但是并没有命中" + targetName +"。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
