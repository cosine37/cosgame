package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class DiaoChan extends Card {
	public DiaoChan() {
		name = "貂蟬";
		courtesy = "";
		img = "DiaoChan";
		title = "巾幗英雄";
		faction = Consts.QUN;
		
		desc = "王道+1，霸道+1，驱逐酒馆中的所有武将，从墓地填充酒馆空位。";
	}
	
	
	public void play(List<Integer> targets) {
		super.play(targets);
		board.moveHan(1);
		board.moveWei(1);
		int i;
		for (i=0;i<board.getTavern().size();i++) {
			Card c = board.takeFromTavern(i);
			if (c != null) {
				board.getExile().add(c);
				board.log(c.getName() + "被驱逐了。", c, "貂蝉驱逐");
			}
		}
		for (i=0;i<board.getTavern().size();i++) {
			if (board.getTomb().size() == 0) break;
			Card c = board.getTomb().remove(0);
			board.getTavern().set(i, c);
			board.log(c.getName() + "进入了酒馆。", c, "加入酒馆");
		}
	}
}
