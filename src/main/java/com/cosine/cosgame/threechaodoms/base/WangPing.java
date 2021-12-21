package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class WangPing extends Card {
	public WangPing() {
		name = "王平";
		courtesy = "子均";
		img = "WangPing";
		title = "警朗有思理";
		faction = Consts.HAN;
		
		desc = "将酒馆中的一张牌置于你的监狱，驱逐其他酒馆中的牌。";
		
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
			int x = targets.get(0);
			Card c = board.takeFromTavern(x);
			player.putInJail(c);
			for (int i=0;i<board.getTavern().size();i++) {
				if (board.getTavern().get(i).isBlankSpace() == false) {
					Card tc = board.takeFromTavern(i);
					board.getExile().add(tc);
				}
			}
		}
	}
}
