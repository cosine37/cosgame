package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class JianYong extends Card {
	public JianYong() {
		name = "簡雍";
		courtesy = "憲和";
		img = "JianYong";
		title = "彼有器具";
		faction = Consts.HAN;
		
		desc = "若你从酒馆中驱逐一张群势力武将，霸道-2。";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		options.add("不驱逐，无事发生");
		for (int i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			if (c.getFaction() == Consts.QUN) {
				options.add("驱逐" + c.getName() + "，霸道-2");
			}
			
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x == 0) return;
			int t = 0;
			for (int i=0;i<board.getTavern().size();i++) {
				Card c = board.getTavern().get(i);
				if (c.getFaction() == Consts.QUN) {
					t++;
					if (t == x) {
						Card tc = board.takeFromTavern(i);
						board.addToExile(tc);
						board.moveWei(-2);
					}
				}
			}
		}
	}
}
