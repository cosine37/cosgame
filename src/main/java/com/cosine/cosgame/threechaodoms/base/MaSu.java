package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class MaSu extends Card {
	public MaSu() {
		name = "馬謖";
		courtesy = "幼常";
		img = "MaSu";
		title = "蜀漢參軍";
		faction = Consts.HAN;
		
		wei = -1;
		han = -1;
		
		desc = "王道-1，霸道-1。将你监狱里的一名武将和你的一张手牌交换。";
		
		playType = Consts.CHOOSEJAILHAND;
		playSubType = Consts.MYCARD;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size()>2) {
			int x = targets.get(0);
			int y = targets.get(2);
			if (x>=0 && x < player.getHand().size() && y>=0 && y<player.getJail().size()) {
				Card c1 = player.getJail().get(y);
				Card c2 = player.getHand().get(x);
				player.getJail().set(y, c2);
				player.getHand().set(x, c1);
				board.log(player.getName() + "将一张手牌与监狱里的一名武将交换了。");
			}
		}
		
	}
}
