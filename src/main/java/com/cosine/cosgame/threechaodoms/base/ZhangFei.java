package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class ZhangFei extends Card {
	public ZhangFei() {
		name = "張飛";
		courtesy = "翼德";
		img = "ZhangFei";
		title = "粗中有細";
		faction = Consts.HAN;
		
		desc = "驱逐酒馆中所有魏势力和群势力武将，每驱逐一张，霸道-1。";
	}
	
	public String getDescDisplay() {
		String ans = desc;
		int i;
		int x = 0;
		for (i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.getFaction() == Consts.QUN || c.getFaction() == Consts.WEI) {
				x++;
			}
		}
		if (x == 0) {
			ans = ans + " (现在打出也没有效果)";
		} else {
			ans = ans + " (霸道-" + x + ")";
		}
		return ans;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int i;
		int x = 0;
		for (i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.getFaction() == Consts.QUN || c.getFaction() == Consts.WEI) {
				Card tc = board.takeFromTavern(i);
				board.getLogger().log(c.getName() + "被驱逐了。", c, "被驱逐");
				board.getExile().add(tc);
				x--;
			}
		}
		board.moveWei(x);
	}
}
