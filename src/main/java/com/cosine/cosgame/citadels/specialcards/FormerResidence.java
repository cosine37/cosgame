package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class FormerResidence extends Card{
	public FormerResidence() {
		super();
		name = "名人故居";
		cost = 3;
		img = "p301";
		color = CitadelsConsts.PURPLE;
	}
	
	public boolean destroyable() {
		return false;
	}
}
