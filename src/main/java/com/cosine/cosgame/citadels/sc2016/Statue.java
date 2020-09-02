package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Statue extends Card{
	public Statue() {
		super();
		name = "雕像";
		cost = 3;
		img = "p302";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public int getExtraScore() {
		int ans = 0;
		if (board.getCrown() == board.getPlayerIndex(player)) {
			ans = 5;
		}
		return ans;
	}
}
