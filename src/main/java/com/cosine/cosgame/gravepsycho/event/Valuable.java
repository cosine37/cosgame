package com.cosine.cosgame.gravepsycho.event;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Valuable extends Event{
	public Valuable() {
		super();
		this.num = 19;
		this.img = "valuable";
		this.name = "价值连城";
		this.desc = "本轮游戏翻出的宝物牌价值额外增加5。";
	}
	
	public boolean singleBackHandle(Player p) {
		for (int i=0;i<board.getRevealed().size();i++) {
			if (board.getRevealed().get(i).getType() == Consts.TREASURE) {
				p.addMoney(5);
			}
		}
		return false;
	}
}
