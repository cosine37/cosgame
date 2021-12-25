package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class XuChu extends Card {
	public XuChu() {
		name = "許褚";
		courtesy = "仲康";
		img = "XuChu";
		title = "武衛將軍";
		faction = Consts.WEI;
		
		desc = "驱逐酒馆中所有蜀势力和群势力武将，每驱逐一张，王道-1。";
	}
	
	public String getDescDisplay() {
		String ans = desc;
		int i;
		int x = 0;
		for (i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.getFaction() == Consts.HAN || c.getFaction() == Consts.QUN) {
				x++;
			}
		}
		if (x == 0) {
			ans = ans + " (现在打出也没有效果)";
		} else {
			ans = ans + " (王道-" + x + ")";
		}
		return ans;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int i;
		int x = 0;
		for (i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.getFaction() == Consts.HAN || c.getFaction() == Consts.QUN) {
				Card tc = board.takeFromTavern(i);
				board.getLogger().log(c.getName() + "被驱逐了。", c, "被驱逐");
				board.addToExile(tc);
				x--;
			}
		}
		board.moveHan(x);
	}
}
