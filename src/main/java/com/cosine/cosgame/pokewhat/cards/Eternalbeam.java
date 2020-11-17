package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class Eternalbeam extends Card{
	public Eternalbeam() {
		num = 0;
		img = "0";
		name = "无极光束";
	}
	
	public void cardEffect() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				
			} else {
				p.hurt(6);
				board.getLogger().logOn(player, p, 6);
			}
		}
	}
}
