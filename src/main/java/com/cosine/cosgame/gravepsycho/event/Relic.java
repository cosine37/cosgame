package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Relic extends Event{
	public Relic() {
		super();
		this.num = 13;
		this.img = "relic";
		this.name = "开门见宝";
		this.desc = "本轮游戏开始时，从牌堆中翻出最靠牌堆顶的宝物。";
	}
	
	public void newRound() {
		int i;
		for (i=0;i<board.getDeck().size();i++) {
			Card c = board.getDeck().get(i);
			if (c.getType() == Consts.TREASURE) {
				board.getDeck().remove(i);
				board.getRevealed().add(c);
				board.getLogger().log("开门见宝！宝藏" + c.getTreasureName() + "出现在了入口处！");
				break;
			}
		}
	}
}
