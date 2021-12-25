package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class ZhuRong extends Card {
	public ZhuRong() {
		name = "祝融";
		courtesy = "";
		img = "ZhuRong";
		title = "擅使飛刀";
		faction = Consts.HAN;
		
		desc = "王道-1或霸道+1，选一势力。翻面一名其他玩家监狱中的武将，若为指定势力，击杀之。";
		
		playType = Consts.CHOOSEJAILOPTIONTWO;
		playSubType = Consts.OTHERPLAYER;
		options.add("王道-1");
		options.add("霸道+1");
		
		options2.add("魏");
		options2.add("蜀");
		options2.add("吴");
		options2.add("群");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >4) {
			int option = targets.get(0);
			int option2 = targets.get(4);
			if (option == 0) {
				board.moveHan(-1);
			} else if (option == 1) {
				board.moveWei(1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (p != null && p.getJail().size() > y) {
				Card c = p.getJail().remove(y);
				if (c.isFaction(option2)) {
					board.addToTomb(c);
					board.log(p.getName() + "监狱中的" + c.getName() + "被击杀了。", c, "祝融击杀");
				} else {
					p.getPlay().add(c);
					board.log(p.getName() + "监狱中的" + c.getName() + "被翻面了。", c, "祝融翻面");
				}
			}
		}
		
	}
}
