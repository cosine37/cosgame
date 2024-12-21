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
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		player.setActive(false);
		player.setPhase(Consts.OFFTURN);
		
	}
	
	public void onDiscard() {
		super.onDiscard();
		player.setActive(false);
	}
}
