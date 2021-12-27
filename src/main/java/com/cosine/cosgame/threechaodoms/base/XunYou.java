package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class XunYou extends Card {
	public XunYou() {
		name = "荀攸";
		courtesy = "公達";
		img = "XunYou";
		title = "外愚內智";
		faction = Consts.WEI;
		
		desc = "王道+1或霸道+1。查看任意两名监狱中的武将。";
		
		playType = Consts.CHOOSETWOJAILOPTION;
		options.add("王道+1");
		options.add("霸道+1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >4) {
			int o = targets.get(0);
			if (o == 0) {
				board.moveHan(1);
			} else if (o == 1) {
				board.moveWei(1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (p != null && p.getJail().size() > y) {
				player.getKnownJails().add(x*10+y);
			}
			x = targets.get(3);
			y = targets.get(4);
			p = board.getPlayerByIndex(x);
			if (p != null && p.getJail().size() > y) {
				player.getKnownJails().add(x*10+y);
			}
			board.log(player.getName() + "观看了监狱中的2名武将。");
		}
		
	}
}
