package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class ChengPu extends Card {
	public ChengPu() {
		name = "程普";
		courtesy = "德謀";
		img = "ChengPu";
		title = "左都督";
		faction = Consts.WU;
		
		desc = "领先标记-1或领先标记-3。";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		int x = player.getJail().size();
		if (x>3) x = 3;
		options = new ArrayList<>();
		if (board.getHanPos() > board.getWeiPos()) {
			options.add("王道-1");
			options.add("王道-3");
		} else if (board.getHanPos() < board.getWeiPos()) {
			options.add("霸道-1");
			options.add("霸道-3");
		} else {
		}
		return options;
	}
	
	public String getDescDisplay() {
		String ans = desc;
		if (board.getHanPos() > board.getWeiPos()) {
			ans = ans + " (王道-1或王道-3)";
		} else if (board.getHanPos() < board.getWeiPos()) {
			ans = ans + " (霸道-1或霸道-3)";
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
			if (option == 0) {
				x = -1;
			} else if (option == 1) {
				x = -3;
			}
			if (board.getHanPos() > board.getWeiPos()) {
				board.moveHan(x);
			} else if (board.getHanPos() < board.getWeiPos()) {
				board.moveWei(x);
			} else {
			}
		}
	}
}
