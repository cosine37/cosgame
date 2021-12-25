package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class ZhangLiao extends Card {
	public ZhangLiao() {
		name = "張遼";
		courtesy = "文遠";
		img = "ZhangLiao";
		title = "威震江東";
		faction = Consts.WEI;
		
		han = 1;
		
		desc = "王道+1，获得一名玩家的一张手牌并将其加入监狱。";
		instruction = "请选择一名玩家。";
		
		playType = Consts.CHOOSEPLAYER;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			Player p = board.getPlayerByIndex(x);
			Random rand = new Random();
			int t = rand.nextInt(p.getHand().size());
			Card c = p.getHand().remove(t);
			player.putInJail(c);
			board.log(player.getName() + "从" + p.getName() + "处获得了一张牌并将其加入监狱。");
		}
	}
}
