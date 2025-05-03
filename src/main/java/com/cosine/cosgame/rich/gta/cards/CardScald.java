package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardScald extends Card {
	public CardScald() {
		super();
		id = 84;
		name = "热つ水";
		desc = "成功率：80%；+1starP，对一名玩家造成1点伤害，该玩家获得一张烫烫烫。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 1;
		aim = 80;
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
				
				String ts = "";
				if (tp.fullHand() == false) {
					ts = "，" + tp.getName() + "获得了一张烫烫烫";
				}

				board.getLogger().log(player.getName() + " 对 " + targetName + " 造成了 " + x + " 点伤害" + ts);
				board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
				board.setBroadcastMsg(player.getName() + "使用了热つ水，对" + targetName + "造成了" + x + "点伤害，" + player.getName() + "获得了1点通缉值" + ts + "。");
				
				player.hurt(tp, x);
				player.addStar(1);
				tp.addCard(new CardTTT());
				
			} else {
				board.getLogger().log(player.getName() + " 没有命中 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了热つ水，但是并没有命中" + targetName +"。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
