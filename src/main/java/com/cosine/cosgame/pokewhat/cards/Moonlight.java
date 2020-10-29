package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;

class Moonlight extends Card{
	public Moonlight() {
		num = 3;
		img = "3";
		name = "月光";
	}
	
	public void cardEffect() {
		Random rand = new Random();
		int x = rand.nextInt(3) + 1;
		player.recover(x);
	}
}
