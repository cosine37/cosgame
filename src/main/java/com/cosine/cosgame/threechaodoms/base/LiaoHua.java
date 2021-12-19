package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class LiaoHua extends Card {
	public LiaoHua() {
		name = "廖化";
		courtesy = "元儉";
		img = "LiaoHua";
		title = "以果烈稱";
		faction = Consts.HAN;
		
		desc = "王道+X或霸道-X。X为你阵面非蜀势力武将的数量，X最大为3.";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		int x = 0;
		for (int i=0;i<player.getPlay().size();i++) {
			Card c = player.getPlay().get(i);
			if (c.getFaction() != Consts.HAN) {
				x++;
			}
		}
		if (x>3) x = 3;
		options = new ArrayList<>();
		options.add("王道+"+x);
		options.add("霸道-"+x);
		return options;
	}
	
	public String getDescDisplay() {
		int x = 0;
		for (int i=0;i<player.getPlay().size();i++) {
			Card c = player.getPlay().get(i);
			if (c.getFaction() != Consts.HAN) {
				x++;
			}
		}
		if (x>3) x = 3;
		desc = desc + " (X为" + x + ")";
		return desc;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int x = 0;
		for (int i=0;i<player.getPlay().size();i++) {
			Card c = player.getPlay().get(i);
			if (c.getFaction() != Consts.HAN) {
				x++;
			}
		}
		if (x>3) x = 3;
		if (targets.size() > 0) {
			int option = targets.get(0);
			if (option == 0) {
				board.moveHan(x);
			} else if (option == 1) {
				board.moveWei(0-x);
			}
		}
	}
}
