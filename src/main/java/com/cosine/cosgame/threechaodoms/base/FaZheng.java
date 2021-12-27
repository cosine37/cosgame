package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class FaZheng extends Card {
	public FaZheng() {
		name = "法正";
		courtesy = "孝直";
		img = "FaZheng";
		title = "恩怨分明";
		faction = Consts.HAN;
		
		han = 1;
		
		desc = "王道+1。将你阵面的一名非蜀势力武将和你的一张手牌交换。";
		
		playType = Consts.CHOOSEPLAYHAND;
		playSubType = Consts.CHOOSEMYPLAYNOTHAN;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size()>2) {
			int x = targets.get(0);
			int y = targets.get(2);
			if (x>=0 && x < player.getHand().size() && y>=0 && y<player.getPlay().size()) {
				Card c1 = player.getPlay().get(y);
				Card c2 = player.getHand().get(x);
				player.getPlay().set(y, c2);
				player.getHand().set(x, c1);
				board.log(player.getName() + "将阵面中的" + c1.getName() + "和手牌中的" + c2.getName() + "交换了。");
			}
		}
		
	}
}
