package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class SunShangXiang extends Card {
	public SunShangXiang() {
		name = "孫尚香";
		courtesy = "";
		img = "SunShangXiang";
		title = "靈澤夫人";
		faction = Consts.WU;
		
		desc = "王道+1或霸道-1，指定一名其他玩家监狱中的武将，观看或将其翻面。";
		
		playType = Consts.CHOOSEJAILOPTIONTWO;
		playSubType = Consts.OTHERPLAYER;
		options.add("王道+1");
		options.add("霸道-1");
		
		options2.add("观看");
		options2.add("翻面");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >4) {
			int option = targets.get(0);
			int option2 = targets.get(4);
			if (option == 0) {
				board.moveHan(1);
			} else if (option == 1) {
				board.moveWei(-1);
			}
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (p != null && p.getJail().size() > y) {
				Card c = p.getJail().get(y);
				if (option2 == 0) {
					player.getKnownJails().add(x*10+y);
					board.log(player.getName() + "观看了" + p.getName() + "监狱中的一张牌。");
				} else if (option2 == 1){
					c = p.getJail().remove(y);
					p.getPlay().add(c);
					board.log(p.getName() + "监狱中的" + c.getName() + "被翻面了。", c, "孙尚香翻面");
				}
			}
		}
		
	}
}
