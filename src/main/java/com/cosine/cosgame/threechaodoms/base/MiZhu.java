package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class MiZhu extends Card {
	public MiZhu() {
		name = "糜竺";
		courtesy = "子仲";
		img = "MiZhu";
		title = "安漢雍容";
		faction = Consts.HAN;
		
		desc = "如果王道>霸道，王道+2，否则王道+1。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (board.getHanPos() > board.getWeiPos()) {
			board.moveHan(2);
		} else {
			board.moveHan(1);
		}
	}
}
