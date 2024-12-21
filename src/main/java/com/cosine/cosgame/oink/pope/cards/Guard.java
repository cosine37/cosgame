package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;

public class Guard extends Card{
	public Guard() {
		super();
		this.num = 4;
		this.name = "守卫";
		this.img = "Guard";
		this.desc = "到下一回合开始前，所有牌对你无效。";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		player.setProtect(true);
		player.setPhase(Consts.OFFTURN);
	}
}
