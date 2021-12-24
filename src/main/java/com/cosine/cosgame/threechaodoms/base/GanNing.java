package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class GanNing extends Card {
	public GanNing() {
		name = "甘寧";
		courtesy = "興霸";
		img = "GanNing";
		title = "銜枚墜鈴";
		faction = Consts.WU;
		
		desc = "王道+2和/或霸道+2。击杀一名其他玩家阵面的武将。";
		
		playType = Consts.CHOOSEPLAYOPTION;
		playSubType = Consts.OTHERPLAYER;
		
		options.add("王道+2");
		options.add("霸道+2");
		options.add("王道+2，霸道+2");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			int o = targets.get(0);
			if (o == 0) {
				board.moveHan(2);
			} else if (o == 1) {
				board.moveWei(2);
			} else if (o == 2) {
				board.moveHan(2);
				board.moveWei(2);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y) {
				Card c = p.getPlay().remove(y);
				board.addToTomb(c);
				board.log(p.getName() + "阵面的" + c.getName() + "被击杀了。", c, "甘宁击杀");
			}
		}
		
	}
}
