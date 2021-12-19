package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class ChengYu extends Card {
	public ChengYu() {
		name = "程昱";
		courtesy = "仲德";
		img = "ChengYu";
		title = "謀定後動";
		faction = Consts.WEI;
		
		desc = "王道-X或霸道+X。X为你监狱中武将的数量，X最大为3.";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		int x = player.getJail().size();
		if (x>3) x = 3;
		options = new ArrayList<>();
		options.add("王道-"+x);
		options.add("霸道+"+x);
		return options;
	}
	
	public String getDescDisplay() {
		int x = player.getJail().size();
		if (x>3) x = 3;
		desc = desc + " (X为" + x + ")";
		return desc;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int x = player.getJail().size();
		if (x>3) x = 3;
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(0-x);
			} else if (option == 1) {
				board.moveWei(x);
			}
		}
	}
}
