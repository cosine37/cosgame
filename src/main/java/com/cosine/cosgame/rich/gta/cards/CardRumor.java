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
		desc = "指定一名玩家，有70%的几率增加该玩家1点通缉值。消耗。";
		rarity = 0;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			Random rand = new Random();
			int x = rand.nextInt(100);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			if (x<70) {
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
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
