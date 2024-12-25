package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;

public class Pope extends Card{
	public Pope() {
		super();
		this.num = 9;
		this.name = "教宗";
		this.img = "Pope";
		this.desc = "若你打出或弃置该牌，你出局。";
		this.color = "goldenrod";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay();
		log("纳尼？居然暴露了教宗！" + player.getName() + "不可饶恕！");
		player.inactivate();
		
	}
	
	public void onDiscard() {
		super.onDiscard();
		log("纳尼？居然暴露了教宗！" + player.getName() + "不可饶恕！");
		player.inactivate();
	}
}
