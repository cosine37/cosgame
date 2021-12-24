package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class WenChou extends Card {
	public WenChou() {
		name = "文醜";
		courtesy = "";
		img = "WenChou";
		title = "一夫之勇";
		faction = Consts.QUN;
		
		desc = "王道-1或霸道-1。击杀任意一名玩家监狱中的一名武将。";
		
		playType = Consts.CHOOSEJAILOPTION;
		options.add("王道-1");
		options.add("霸道-1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			int o = targets.get(0);
			if (o == 0) {
				board.moveHan(-1);
			} else if (o == 1) {
				board.moveWei(-1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (p != null && p.getJail().size() > y) {
				Card c = p.getJail().remove(y);
				board.addToTomb(c);
				board.log(p.getName() + "监狱中的" + c.getName() + "被击杀了。", c, "文丑击杀");
			}
		}
		
	}
}
