package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Card;

public class ZhaoJi extends Card {
	public ZhaoJi() {
		super();
		nickname = "宋徽宗";
		name = "赵佶";
		img = "ZhaoJi";
		atk = 3;
		hp = 3;
	}
	
	public void ruleChange() {
		board.disableStealOrRaid();
	}
}
