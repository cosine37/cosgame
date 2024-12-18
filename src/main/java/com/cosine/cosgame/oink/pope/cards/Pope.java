package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;

public class Pope extends Card{
	public Pope() {
		super();
		this.num = 9;
		this.name = "教宗";
		this.img = "Pope";
		this.desc = "若你打出或弃置该牌，你出局。";
	}
	
	public void onPlay() {
		player.setActive(false);
	}
	
	public void onDiscard() {
		player.setActive(false);
	}
}
