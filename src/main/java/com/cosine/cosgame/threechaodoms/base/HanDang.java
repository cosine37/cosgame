package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class HanDang extends Card {
	public HanDang() {
		name = "韓當";
		courtesy = "義公";
		img = "HanDang";
		title = "昭武將軍";
		faction = Consts.WU;
		
		desc = "领先标记-1或落后标记+2。";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		int x = player.getJail().size();
		if (x>3) x = 3;
		options = new ArrayList<>();
		if (board.getHanPos() > board.getWeiPos()) {
			options.add("王道-1");
			options.add("霸道+2");
		} else if (board.getHanPos() < board.getWeiPos()) {
			options.add("王道+2");
			options.add("霸道-1");
		} else {
		}
		return options;
	}
	
	public String getDescDisplay() {
		String ans = desc;
		if (board.getHanPos() > board.getWeiPos()) {
			ans = ans + " (王道-1或霸道+2)";
		} else if (board.getHanPos() < board.getWeiPos()) {
			ans = ans + " (王道+2或霸道-1)";
		} else {
			ans = ans + " (现在打出没有效果)";
		}
		return ans;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int option = targets.get(0);
			int x = 0;
			int y = 0;
			if (board.getHanPos() > board.getWeiPos()) {
				x = -1;
				y = 2;
			} else if (board.getHanPos() < board.getWeiPos()) {
				x = 2;
				y = -1;
			} else {
			}
			if (option == 0) {
				board.moveHan(x);
			} else if (option == 1) {
				board.moveWei(y);
			}
		}
	}
}
