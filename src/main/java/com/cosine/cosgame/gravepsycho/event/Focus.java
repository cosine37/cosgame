package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Focus extends Event{
	public Focus() {
		super();
		this.num = 9;
		this.img = "focus";
		this.name = "痴迷探险";
		this.desc = "探险时玩家不会分发钱币，只有返回营地时才能获取。";
	}
	
	public boolean distributeCoins(int x) {
		int leftover = board.getLeftover();
		leftover = leftover+x;
		board.setLeftover(leftover);
		board.getLogger().log("因为过度专注探险，钱币被所有人无视了");
		return true;
	}
}
