package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class TaiShiCi extends Card {
	public TaiShiCi() {
		name = "太史慈";
		courtesy = "子義";
		img = "TaiShiCi";
		title = "篤於信義";
		faction = Consts.WU;
		
		desc = "王道-X和/或霸道-X。选择一个势力，驱逐酒馆中所有相应势力的武将。X为驱逐的数量。";
		
		playType = Consts.CHOOSETWO;
		options.add("王道-X");
		options.add("霸道-X");
		options.add("王道和霸道-X");
		
		options2.add("魏");
		options2.add("蜀");
		options2.add("吴");
		options2.add("群");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 4) {
			int option = targets.get(0);
			int option2 = targets.get(4);
			int x = 0;
			for (int i=0;i<board.getTavern().size();i++) {
				if (board.getTavern().get(i).isFaction(option2)) {
					Card tc = board.takeFromTavern(i);
					board.getLogger().log(tc.getName() + "被驱逐了。", tc, "太史慈驱逐");
					board.addToExile(tc);
					x++;
				}
			}
			if (option == 0) {
				board.moveHan(0-x);
			} else if (option == 1) {
				board.moveWei(0-x);
			} else if (option == 2) {
				board.moveHan(0-x);
				board.moveWei(0-x);
			}
		}
	}
}
