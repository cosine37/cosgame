package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;

public class DuoColorCard extends Card {
	public DuoColorCard(String name) {
		super(name);
	}
	
	public int getScore() {
		return super.getScore()-2;
	}
}
