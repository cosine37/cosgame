package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class HuaTuo extends Card {
	public HuaTuo() {
		name = "華佗";
		courtesy = "元化";
		img = "HuaTuo";
		title = "外科聖手";
		faction = Consts.QUN;
		
		han = 1;
		wei = 1;
		
		desc = "王道+1，霸道+1，将墓地中的一名武将加入你的手牌。";
		
		playType = Consts.CHOOSETOMB;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			Card c = board.takeFromTomb(x);
			player.getHand().add(c);
			board.getLogger().log(player.getName() + "把" + c.getName() + "加入了手牌。", c, "加入手牌");
		}
	}
}
