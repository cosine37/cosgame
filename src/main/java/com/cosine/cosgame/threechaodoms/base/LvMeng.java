package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class LvMeng extends Card {
	public LvMeng() {
		name = "吕蒙";
		courtesy = "子明";
		img = "LvMeng";
		title = "吳下阿蒙";
		faction = Consts.WU;
		
		desc = "王道-2或霸道-1。";
		
		playType = Consts.CHOOSEONE;
		options.add("王道-2");
		options.add("霸道-1");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(-2);
			} else if (option == 1) {
				board.moveWei(-1);
			}
		}
	}
}
