package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class LvBu extends Card {
	public LvBu() {
		name = "吕布";
		courtesy = "奉先";
		img = "LvBu";
		title = "吕温侯";
		faction = Consts.QUN;
		
		desc = "王道+2和/或霸道+2。击杀你和另一名玩家阵面的各一名武将。";
		
		playType = Consts.CHOOSEPLAYMEOTHEROPTION;
		options.add("王道+2");
		options.add("霸道+2");
		options.add("王道+2，霸道+2");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >3) {
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
			int z = targets.get(3);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y && player.getPlay().size() > z) {
				Card c1 = p.getPlay().remove(y);
				Card c2 = player.getPlay().remove(z);
				board.addToTomb(c1);
				board.addToTomb(c2);
				board.log(p.getName() + "阵面的" + c1.getName() + "与" + player.getName() + "阵面的" + c2.getName() + "被吕布击杀了。");
			}
		}
		
	}
}
