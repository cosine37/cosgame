package com.cosine.cosgame.citadels.sc2016;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Comb extends Card{
	public Comb() {
		super();
		name = "梳篦";
		cost = 0;
		img = "p001";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
		buildable = false;
	}
	
	public int getSecretScore() {
		return 3;
	}
}
