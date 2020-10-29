package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Card;

public class LifeDew extends Card{
	public LifeDew() {
		num = 8;
		img = "8";
		name = "生命水滴";
	}
	
	public void cardEffect() {
		player.recover(1);
	}
}
