package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class StupidSun extends Card{
	public StupidSun() {
		super();
		name = "神亭岭";
		cost = 2;
		img = "p204";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onAnotherBuild(Card c) {
		String name = player.getName();
		if (c.getColor() == CitadelsConsts.RED
				|| c.getColor() == CitadelsConsts.BLUERED
				|| c.getColor() == CitadelsConsts.GREENRED
				|| c.getColor() == CitadelsConsts.REDYELLOW) {
			player.draw(1);
			board.log("因为 神亭岭 的效果，" + name + "十分激昂地摸了一张牌。");
		}
	}
}
