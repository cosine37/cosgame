package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Villa extends Card{
	public Villa() {
		super();
		name = "别墅区";
		cost = 4;
		img = "p407";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void alterPlayerAbility() {
		player.setBeautify5Up(true);
	}
}
