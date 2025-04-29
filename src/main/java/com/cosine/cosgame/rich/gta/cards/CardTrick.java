package com.cosine.cosgame.rich.gta.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardTrick extends Card {
	public CardTrick() {
		super();
		id = 66;
		name = "恶作剧";
		desc = "与一名玩家交换所有手牌，获得等同于牌差的starP。消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			int n = tp.getHand().size() - player.getHand().size();
			if (n<0) n = 0-n;
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) targetName = "自己";
			
			int i;
			List<Card> c1 = new ArrayList<>();
			List<Card> c2 = new ArrayList<>();
			for (i=0;i<tp.getHand().size();i++) {
				c1.add(tp.getHand().get(i));
			}
			for (i=0;i<player.getHand().size();i++) {
				c2.add(player.getHand().get(i));
			}
			
			if (n == 0) {
				board.getLogger().log(player.getName() + " 与 " + tp.getName() + " 交换了所有手牌");
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，与" + targetName + "交换了所有手牌。");
			} else {
				board.getLogger().log(player.getName() + " 与 " + tp.getName() + " 交换了所有手牌， " + player.getName() + " 获得了 " + n + " 点通缉值");
				board.setBroadcastMsg(player.getName() + "打出了" + name + "，与" + targetName + "交换了所有手牌，" + player.getName() + "获得了" + n + "点通缉值，真是脏的飞起！");
			}
			
			tp.setHand(c2);
			player.setHand(c1);
			if (n>0) player.addStar(n);
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
