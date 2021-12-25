package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class HuangZhong extends Card {
	public HuangZhong() {
		name = "黄忠";
		courtesy = "漢升";
		img = "HuangZhong";
		title = "人老心不老";
		faction = Consts.HAN;
		
		desc = "若你从酒馆中驱逐一名魏势力武将，王道+2。";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		options.add("不驱逐，无事发生");
		for (int i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.isFaction(Consts.WEI)) {
				options.add("驱逐" + c.getName() + "，王道+2");
			}
			
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x == 0) return;
			int t = 0;
			for (int i=0;i<board.getTavern().size();i++) {
				Card c = board.getTavern().get(i);
				if (c.isFaction(Consts.WEI)) {
					t++;
					if (t == x) {
						Card tc = board.takeFromTavern(i);
						board.getLogger().log(c.getName() + "被驱逐了。", c, "被驱逐");
						board.addToExile(tc);
						board.moveHan(2);
					}
				}
			}
		}
	}
}
