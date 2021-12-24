package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class JiLing extends Card {
	public JiLing() {
		name = "紀靈";
		courtesy = "";
		img = "JiLing";
		title = "手提三尖";
		faction = Consts.QUN;
		
		han = -1;
		wei = -1;
		
		desc = "王道-1，霸道-1。击杀任意一名玩家阵面的一名武将。";
		
		playType = Consts.CHOOSEPLAY;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y) {
				Card c = p.getPlay().remove(y);
				board.addToTomb(c);
				board.log(p.getName() + "阵面的" + c.getName() + "被击杀了。", c, "纪灵击杀");
			}
		}
		
	}
}
