package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class CaoHong extends Card {
	public CaoHong() {
		name = "曹洪";
		courtesy = "子廉";
		img = "CaoHong";
		title = "曹家白鵠";
		faction = Consts.WEI;
		
		wei = 1;
		
		desc = "霸道+1。将另一名玩家阵面或监狱中的一名武将翻面。";
		
		playType = Consts.CHOOSEPLAYJAIL;
		playSubType = Consts.OTHERPLAYER;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size()>3) {
			int x = targets.get(1);
			int y1 = targets.get(2);
			int y2 = targets.get(3);
			if (y1 != -1) {
				int y = y1;
				Player p = board.getPlayerByIndex(x);
				if (p != null && p.getPlay().size() > y) {
					Card c = p.getPlay().remove(y);
					p.getJail().add(c);
					board.log(p.getName() + "阵面的" + c.getName() + "被翻面了。", c, "曹洪翻面");
				}
			} else if (y2!=-1) {
				int y = y2;
				Player p = board.getPlayerByIndex(x);
				if (p != null && p.getJail().size() > y) {
					Card c = p.getJail().remove(y);
					p.getPlay().add(c);
					board.log(p.getName() + "监狱中的" + c.getName() + "被翻面了。", c, "曹洪翻面");
				}
			}
			
		}
		
	}
}
