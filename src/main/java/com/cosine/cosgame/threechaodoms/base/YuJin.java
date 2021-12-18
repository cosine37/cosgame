package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class YuJin extends Card {
	public YuJin() {
		name = "于禁";
		courtesy = "文則";
		img = "YuJin";
		title = "最号毅重";
		faction = Consts.WEI;
		
		desc = "如果王道>霸道，王道-2，否则王道-1。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (board.getHanPos() > board.getWeiPos()) {
			board.moveHan(-2);
		} else {
			board.moveHan(-1);
		}
	}
}
