package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class SunQuan extends Card {
	public SunQuan() {
		name = "孫權";
		courtesy = "仲謀";
		img = "SunQuan";
		title = "碧眼紫髯";
		faction = Consts.WU;
		
		desc = "王道-2和/或霸道-2。";
		
		playType = Consts.CHOOSEONE;
		options.add("王道-2");
		options.add("霸道-2");
		options.add("王道和霸道-2");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(-2);
			} else if (option == 1) {
				board.moveWei(-2);
			} else if (option == 2) {
				board.moveHan(-2);
				board.moveWei(-2);
			}
		}
	}
}
