package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardCanadaGoose extends Card {
	public CardCanadaGoose() {
		super();
		id = 20008;
		name = "加拿大鹅";
		desc = "对一名玩家造成1点伤害。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 1;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			int x = getFinalAttack();
			board.getLogger().log(player.getName() + " 对 " + tp.getName() + " 造成了 " + x + " 点伤害");
			board.setBroadcastMsg(player.getName() + "召唤了加拿大鹅，对" + targetName + "造成了" + x + "点伤害。");
			
			player.hurt(tp, x);
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
