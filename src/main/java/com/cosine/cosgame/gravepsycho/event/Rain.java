package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Rain extends Event{
	public Rain() {
		super();
		this.num = 16;
		this.img = "rain";
		this.name = "下钱雨啦";
		this.desc = "每次分发钱币后，归途剩余钱币将额外增加两枚。";
	}
	
	public boolean distributeCoins(int x) {
		int t = board.getLeftover()+2;
		board.setLeftover(t);
		board.getLogger().log("下钱雨啦！两枚钱币空降归途");
		return false;
	}
}
