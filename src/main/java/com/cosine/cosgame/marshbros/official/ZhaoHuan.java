package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Card;

public class ZhaoHuan extends Card {
	public ZhaoHuan() {
		super();
		nickname = "宋钦宗";
		name = "赵桓";
		img = "ZhaoHuan";
		atk = 3;
		hp = 3;
	}
	
	public void ruleChange() {
		board.disableStealOrRaid();
	}
}
