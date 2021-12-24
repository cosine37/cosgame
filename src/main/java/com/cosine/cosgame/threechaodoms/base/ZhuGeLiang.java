package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class ZhuGeLiang extends Card {
	public ZhuGeLiang() {
		name = "諸葛亮";
		courtesy = "孔明";
		img = "ZhuGeLiang";
		title = "武嚮候";
		faction = Consts.HAN;
		
		desc = "王道+1或霸道-1，获得一名玩家的一张手牌并将其加入你的手牌。";
		instruction = "请选择一名玩家。";
		
		playType = Consts.CHOOSEPLAYEROPTION;
		
		options.add("王道+1");
		options.add("霸道-1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 1) {
			int o = targets.get(0);
			int x = targets.get(1);
			if (o == 0) {
				board.moveHan(1);
			} else if (o==1) {
				board.moveWei(-1);
			}
			Player p = board.getPlayerByIndex(x);
			Random rand = new Random();
			int t = rand.nextInt(p.getHand().size());
			Card c = p.getHand().remove(t);
			player.getHand().add(c);
			board.log(player.getName() + "从" + p.getName() + "处获得了一张牌并将其加入手牌。");
		}
	}
}
