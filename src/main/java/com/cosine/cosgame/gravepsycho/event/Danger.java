package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Danger extends Event{
	public Danger() {
		super();
		this.num = 5;
		this.img = "danger";
		this.name = "危机四伏";
		this.desc = "当翻出4张不同名灾难牌时，灾难也会发生。";
	}
	
	public boolean disaster(boolean f) {
		boolean ans = f;
		int x = 0;
		for (int i=0;i<board.getRevealed().size();i++) {
			if (board.getRevealed().get(i).getType() == Consts.DISASTER) {
				x++;
			}
		}
		if (x >= 4) ans=true;
		return ans;
	}
}
