package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardHydroPump extends Card {
	public CardHydroPump() {
		super();
		id = 46;
		name = "水炮卡";
		desc = "成功率：80%；+1starP，对一名玩家造成2点伤害且该玩家失去1张手牌。消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 2;
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

				board.getLogger().log(player.getName() + " 对 " + targetName + " 造成了 " + x + " 点伤害，且" + targetName + "失去了 1 张随机手牌");
				board.getLogger().log(player.getName() + " 获得了 1 点通缉值");
				
				String s1 = "点伤害，并失去了一张随机手牌。";
				board.setBroadcastMsg(player.getName() + "使用了水炮卡，对" + targetName + "猛烈地喷射大量水流，" + tp.getName() + "受到了" + x + s1 + player.getName() + "获得了1点通缉值。");
				
				player.hurt(tp, x);
				tp.discardRandom(1);
				player.addStar(1);
			} else {
				board.getLogger().log(player.getName() + " 但是没有命中 " + targetName);
				board.setBroadcastMsg(player.getName() + "使用了水炮卡，但是并没有命中" + targetName +"。");
			}
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
