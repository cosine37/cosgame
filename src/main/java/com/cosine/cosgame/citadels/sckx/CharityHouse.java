package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CharityHouse extends Card{
	public CharityHouse() {
		super();
		name = "福利院";
		cost = 6;
		img = "p613";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void alterPlayerAbility() {
		player.setBeautifyUpTo2(true);
	}
}
