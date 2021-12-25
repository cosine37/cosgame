package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class YueJin extends Card {
	public YueJin() {
		name = "樂進";
		courtesy = "文謙";
		img = "YueJin";
		title = "驍果顯名";
		faction = Consts.WEI;
		
		desc = "如果你阵面有吴势力武将，霸道+2。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int i;
		boolean flag = false;
		for (i=0;i<player.getPlay().size();i++) {
			Card c = player.getPlay().get(i);
			if (c.isFaction(Consts.WU)) {
				flag = true;
			}
		}
		if (flag) {
			board.moveWei(2);
		}
	}
}
