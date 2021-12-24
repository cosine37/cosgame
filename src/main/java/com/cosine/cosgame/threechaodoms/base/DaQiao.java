package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class DaQiao extends Card {
	public DaQiao() {
		name = "大喬";
		courtesy = "";
		img = "DaQiao";
		title = "國色流離";
		faction = Consts.WU;
		
		desc = "王道+1，霸道+1，将酒馆中的一名武将置于你的阵面。";
		
		playType = Consts.CHOOSEONE;
	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		for (int i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			options.add("招募" + c.getName());
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			board.moveHan(1);
			board.moveWei(1);
			int x = targets.get(0);
			Card c = board.takeFromTavern(x);
			player.getPlay().add(c);
			board.getLogger().log(player.getName() + "把" + c.getName() + "加入了阵面。", c, "加入阵面");
		}
	}
}
