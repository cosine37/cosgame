package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class GlobalHarbor extends Card{
	public GlobalHarbor() {
		super();
		name = "环球港";
		cost = 6;
		img = "p604";
		color = CitadelsConsts.PURPLE;
	}
	
	public boolean omniColor() {
		return true;
	}
}
