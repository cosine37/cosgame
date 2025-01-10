package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Panic extends Event{
	public Panic() {
		super();
		this.num = 14;
		this.img = "panic";
		this.name = "开幕雷击";
		this.desc = "本轮游戏开始时，从牌堆底翻出两张不同名的灾难牌。";
	}
	
	public void newRound() {
		int i;
		int x = -1;
		String s = "开幕雷击！两个灾难，";
		for (i=board.getDeck().size()-1;i>=0;i--) {
			Card c = board.getDeck().get(i);
			if (c.getType() == Consts.DISASTER) {
				if (x == -1) {
					x = c.getNum();
					board.getDeck().remove(i);
					board.getRevealed().add(c);
					s = s+c.getDisasterName();
				} else if (x == c.getNum()) {
					
				} else {
					board.getDeck().remove(i);
					board.getRevealed().add(c);
					s = s+"和"+c.getDisasterName()+"，接近！";
					break;
				}
			}
		}
		board.getLogger().log(s);
	}
}
