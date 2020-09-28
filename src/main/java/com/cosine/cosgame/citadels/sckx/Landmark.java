package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Landmark extends Card{
	public Landmark() {
		super();
		name = "莱蒙都会";
		cost = 5;
		img = "p517";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onAnotherBuild(Card c) {
		String name = player.getName();
		if (c.getColor() == CitadelsConsts.GREEN
				|| c.getColor() == CitadelsConsts.GREENBLUE
				|| c.getColor() == CitadelsConsts.GREENRED
				|| c.getColor() == CitadelsConsts.GREENYELLOW
				|| c.getColor() == CitadelsConsts.GREENPURPLE) {
			player.addCoin(1);
			board.log("因为 莱蒙都会 的效果，" + name + "十分贪婪地获得了1￥。");
		}
	}
}
