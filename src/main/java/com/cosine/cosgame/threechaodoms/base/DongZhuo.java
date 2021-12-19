package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class DongZhuo extends Card {
	public DongZhuo() {
		name = "董卓";
		courtesy = "仲穎";
		img = "DongZhuo";
		title = "暴虐不仁";
		faction = Consts.QUN;
		
		desc = "王道+X，霸道+X。X为墓地中武将的数量，X最大为3.";
	}
	
	public String getDescDisplay() {
		int x = board.getTomb().size();
		if (x>3) x = 3;
		desc = desc + " (X为" + x + ")";
		return desc;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		int x = board.getTomb().size();
		if (x>3) x = 3;
		board.moveHan(x);
		board.moveWei(3);
	}
}
