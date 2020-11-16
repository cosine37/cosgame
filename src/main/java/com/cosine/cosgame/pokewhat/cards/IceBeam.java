package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class IceBeam extends Card{
	public IceBeam() {
		num = 6;
		img = "6";
		name = "冰冻光束";
	}
	
	public void cardEffect() {
		Player p = player.nextPlayer();
		p.hurt(1);
		board.getLogger().logOn(player, p, 1);
	}
}
