package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class ZhonglianBuilding extends Card{
	public ZhonglianBuilding() {
		super();
		name = "中联大厦";
		cost = 4;
		img = "p406";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void endTurnEffect() {
		if (board.getCoins() != 0) {
			int x = board.takeCoins(1);
			if (x>0) {
				beautify();
			}
			if (beautifyLevel >= 4) {
				Card c = player.getBuilt().remove(index);
				board.addCoin(c.getBeautifyLevel());
				c.setBeautifyLevel(0);
				board.bottomDeck(c);
				board.log(player.getName() + "的 中联大厦 因过度建造被拆除。");
			} else {
				if (x>0) {
					board.log(player.getName() + "的 中联大厦 价值上升。");
				}
			}
		}
	}
}
