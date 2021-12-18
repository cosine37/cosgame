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
		
		desc = "翻开隐居堆顶的一张牌，若该角色不为群势力，王道-3，霸道-3。";
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		boolean flag = false;
		if (board.getExile().size()>0) {
			Card c = board.getExile().get(0);
			if (c.getFaction() != Consts.QUN) {
				flag = true;
			}
		}
		if (flag) {
			board.moveHan(-3);
			board.moveWei(-3);
		}
	}
}
