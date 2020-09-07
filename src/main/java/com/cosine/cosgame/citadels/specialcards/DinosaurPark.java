package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class DinosaurPark extends Card{
	public DinosaurPark() {
		super();
		name = "恐龙城";
		cost = 6;
		img = "p601";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getScore() {
		return super.getScore()+2;
	}
}
