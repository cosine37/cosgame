package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class XuSheng extends Card {
	public XuSheng() {
		name = "徐盛";
		courtesy = "";
		img = "XuSheng";
		title = "大壯";
		faction = Consts.WU;
		
		desc = "王道+1或霸道+1。击杀任意一名玩家阵面的一名武将。";
		
		playType = Consts.CHOOSEPLAYOPTION;
		options.add("王道+1");
		options.add("霸道+1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			int o = targets.get(0);
			if (o == 0) {
				board.moveHan(1);
			} else if (o == 1) {
				board.moveWei(1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y) {
				Card c = p.getPlay().remove(y);
				board.addToTomb(c);
				board.log(p.getName() + "阵面的" + c.getName() + "被击杀了。", c, "徐盛击杀");
			}
		}
		
	}
}
