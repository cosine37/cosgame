package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Guard extends Event{
	public Guard() {
		super();
		this.num = 17;
		this.img = "guard";
		this.name = "幽灵守卫";
		this.desc = "本轮翻出的第3张灾难牌将会被无视。";
	}
	
	public boolean disaster(boolean f) {
		if (f) {
			boolean ans = false;
			int[] a = {0,0,0,0,0};
			List<Card> revealed = board.getRevealed();
			int numDisaster = 0;
			for (int i=0;i<revealed.size();i++) {
				if (revealed.get(i).getType() == Consts.DISASTER) {
					numDisaster++;
					if (numDisaster == 3) continue;
					int x = revealed.get(i).getNum();
					a[x]++;
					if (a[x] >= 2) {
						ans = true;
						break;
					}
				}
			}
			return ans;
		} else {
			return false;
		}
	}
	
	public void onReveal(Card c) {
		if (c.getType() == Consts.DISASTER) {
			List<Card> revealed = board.getRevealed();
			int numDisaster = 0;
			for (int i=0;i<revealed.size();i++) {
				if (revealed.get(i).getType() == Consts.DISASTER) {
					numDisaster++;
				}
			}
			if (numDisaster == 3) {
				board.getLogger().log("但是被幽灵守卫无效化了。谢谢你，幽灵守卫！");
			}
		}
	}
}
