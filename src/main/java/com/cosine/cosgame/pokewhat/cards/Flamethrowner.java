package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class Flamethrowner extends Card{
	public Flamethrowner() {
		num = 7;
		img = "7";
		name = "喷射火焰";
	}
	
	public void cardEffect() {
		Player p = player.prevPlayer();
		p.hurt(1);
	}
}
