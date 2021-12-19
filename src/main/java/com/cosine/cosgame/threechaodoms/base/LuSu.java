package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class LuSu extends Card {
	public LuSu() {
		name = "魯肅";
		courtesy = "子敬";
		img = "LuSu";
		title = "聯劉抗曹";
		faction = Consts.WU;
		
		desc = "王道-1或霸道-2。";
		
		playType = Consts.CHOOSEONE;
		options.add("王道-1");
		options.add("霸道-2");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(-1);
			} else if (option == 1) {
				board.moveWei(-2);
			}
		}
	}
}
