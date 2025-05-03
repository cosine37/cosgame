package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardSteamEruption extends Card {
	public CardSteamEruption() {
		super();
		id = 85;
		name = "蒸汽爆炸";
		desc = "对一名玩家造成2点伤害并摧毁载具，该玩家每有一个空手牌位，获得一张烫烫烫。消耗。";
		rarity = 3;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
		attack = 2;
		fontSize = 17;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			int x = getFinalAttack();
			board.getLogger().log(player.getName() + " 对 " + tp.getName() + " 造成了 " + x + " 点伤害， "+tp.getName()+" 失去了载具且" + tp.getName() + "每有一个空手牌位，获得一张烫烫烫");
			board.setBroadcastMsg(player.getName() + "召唤了波尔凯尼恩使用蒸汽爆炸，对" + targetName + "造成了" + x + "点伤害，"+tp.getName()+"失去了载具且" + tp.getName() + "每有一个空手牌位，获得一张烫烫烫。");
			
			player.hurt(tp, x);
			while (tp.fullHand() == false) {
				tp.addCard(new CardTTT());
			}
			player.loseVehicle();
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
