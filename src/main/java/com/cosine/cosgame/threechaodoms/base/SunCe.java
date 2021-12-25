package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class SunCe extends Card {
	public SunCe() {
		name = "孫策";
		courtesy = "伯符";
		img = "SunCe";
		title = "江東小霸王";
		faction = Consts.WU;
		
		desc = "展示隐居堆顶的武将，若该武将不为群势力，王道-3，霸道-3。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		boolean flag = false;
		if (board.getExile().size()>0) {
			Card c = board.getExile().get(0);
			board.getLogger().log("孙笨十分激昂地展示了" + c.getName(), c, "隐居堆顶");
			if (!c.isFaction(Consts.QUN)) {
				flag = true;
			}
		}
		if (flag) {
			board.moveHan(-3);
			board.moveWei(-3);
		}
	}
}
