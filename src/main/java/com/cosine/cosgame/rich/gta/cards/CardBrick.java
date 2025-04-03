package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardBrick extends Card {
	public CardBrick() {
		super();
		id = 18;
		name = "板砖卡";
		desc = "成功率：70%；+2starP，对一名玩家造成2点伤害并摧毁载具。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 2;
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
				board.getLogger().log(player.getName() + " 获得了 2 点通缉值");
				
				String s1 = "点伤害，";
				if (tp.hasVehicle()) {
					s1 = "点伤害且失去了载具，";
				}
				board.setBroadcastMsg(player.getName() + "使用了板砖卡，对" + targetName + "扔出板砖，" + tp.getName() + "受到了" + x + s1 + player.getName() + "获得了2点通缉值。");
				
				player.hurt(tp, x);
				if (tp.hasVehicle()) {
					tp.loseVehicle();
				}
				player.addStar(2);
			} else {
				board.getLogger().log(player.getName() + " 但是没有命中 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了板砖卡，对" + targetName + "扔出板砖，但是并没有命中" + targetName +"。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
