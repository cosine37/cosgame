package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Inn extends Card{
	public Inn() {
		super();
		name = "小旅馆";
		cost = 2;
		img = "p201";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getColorForScore() {
		if (board.getRoundCount() == builtRound) {
			return color;
		} else {
			if (player.missingColor() < 0) {
				return color;
			} else {
				return player.missingColor();
			}
		}
		
	}
}
