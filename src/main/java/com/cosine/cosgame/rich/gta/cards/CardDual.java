package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardDual extends Card {
	public CardDual() {
		super();
		id = 69;
		name = "决斗";
		desc = "+1starP，若指定的玩家手牌稀有度更高，其对你造成1点伤害，否则你对其造成1点伤害。消耗。";
		rarity = 0;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 1;
		fontSize = 16;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			int x = getFinalAttack();
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) {
				targetName = "自己";
				board.getLogger().log(player.getName() + " 对 " + tp.getName() + " 造成了 " + x + " 点伤害");
				board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
				board.setBroadcastMsg(player.getName() + "使用了决斗卡，对自己造成了" + x + "点伤害，" + player.getName() + "获得了1点通缉值。");
				player.hurt(tp, x);
			} else {
				int myHandRarity = player.handRarity();
				int tpHandRarity = tp.handRarity();
				String myHandRarityStr = player.handRarityStr();
				String tpHandRarityStr = tp.handRarityStr();
				
				String logMsg = player.getName() + "拥有" + myHandRarityStr + "，" + tp.getName() + "拥有" + tpHandRarityStr;
				String broadcastMsg = logMsg + "。";
				board.getLogger().log(logMsg);
				if (tpHandRarity>myHandRarity) {
					broadcastMsg = broadcastMsg + tp.getName() + "对" + player.getName() + "造成了" + x + "点伤害。";
					board.getLogger().log(tp.getName() + " 对 " + player.getName() + " 造成了 " + x + " 点伤害");
					tp.hurt(player, x);
				} else {
					broadcastMsg = broadcastMsg + player.getName() + "对" + tp.getName() + "造成了" + x + "点伤害。";
					board.getLogger().log(player.getName() + " 对 " + tp.getName() + " 造成了 " + x + " 点伤害");
					player.hurt(tp, x);
				}
				broadcastMsg = broadcastMsg + player.getName() + "获得了1点通缉值。";
				board.setBroadcastMsg(broadcastMsg);
			}
			
			board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
			
			player.addStar(1);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
