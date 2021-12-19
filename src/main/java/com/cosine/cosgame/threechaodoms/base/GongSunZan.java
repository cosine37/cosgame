package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class GongSunZan extends Card {
	public GongSunZan() {
		name = "公孫瓚";
		courtesy = "伯珪";
		img = "GongSunZan";
		title = "俊美擅辯";
		faction = Consts.QUN;
		
		desc = "王道+2或霸道+2。";
		
		playType = Consts.CHOOSEONE;
		options.add("王道+2");
		options.add("霸道+2");
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(2);
			} else if (option == 1) {
				board.moveWei(2);
			}
		}
	}
}
