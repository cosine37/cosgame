package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class SECenter extends Card{
	public SECenter() {
		super();
		name = "科教城";
		cost = 6;
		img = "p602";
		color = CitadelsConsts.PURPLE;
	}
	
	public int getScore() {
		return 8;
	}
}
