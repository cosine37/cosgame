package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;

public class Mayor extends Card{
	public Mayor() {
		super();
		this.num = 8;
		this.name = "村长";
		this.img = "Mayor";
		this.desc = "无效果。若你的手牌中有女巫或法师，你必须打出这张村长。";
		this.color = "DarkGreen";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay();
		log("无事发生。。。");
		player.setPhase(Consts.OFFTURN);
	}
}
