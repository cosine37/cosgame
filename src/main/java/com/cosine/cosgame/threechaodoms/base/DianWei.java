package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class DianWei extends Card {
	public DianWei() {
		name = "典韋";
		courtesy = "";
		img = "DianWei";
		title = "漢之樊噲";
		faction = Consts.WEI;
		
		desc = "如果你阵面有群势力武将，王道-2。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int i;
		boolean flag = false;
		for (i=0;i<player.getPlay().size();i++) {
			Card c = player.getPlay().get(i);
			if (c.getFaction() == Consts.QUN) {
				flag = true;
			}
		}
		if (flag) {
			board.moveHan(-2);
		}
	}
}
