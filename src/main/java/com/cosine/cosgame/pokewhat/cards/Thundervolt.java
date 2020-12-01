package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class Thundervolt extends Card{
	public Thundervolt() {
		super();
		num = 5;
		img = "5";
		name = "十万伏特";
	}
	
	public void cardEffect() {
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		p1.hurt(1);
		board.getLogger().logOn(player, p1, 1);
		if (!p1.getName().contentEquals(p2.getName())) {
			p2.hurt(1);
			board.getLogger().logOn(player, p2, 1);
		}
	}
}
