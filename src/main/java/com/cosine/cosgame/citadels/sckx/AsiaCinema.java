package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class AsiaCinema extends Card{
	public AsiaCinema() {
		super();
		name = "亚细亚影城";
		cost = 5;
		img = "p512";
		color = CitadelsConsts.GREENPURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		int x = board.takeCoins(2);
		this.beautifyLevel = x;
	}
	
	public void afterTakeActionEffect() {
		String name = player.getName();
		if (beautifyLevel>0) {
			beautifyLevel--;
			board.addCoin(1);
			board.log(name + "的亚细亚影城贬值了。");
		}
	}
	
	public void endTurnEffect() {
		if (beautifyLevel == 0) {
			board.log(player.getName() + "的亚细亚影城严重贬值，失去了其特殊类型。");
			img = "p512-1";
			color = CitadelsConsts.GREEN;
			cost = 3;
		}
		
	}
}
