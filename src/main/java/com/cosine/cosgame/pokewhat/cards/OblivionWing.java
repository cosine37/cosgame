package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class OblivionWing extends Card{
	public OblivionWing() {
		num = 2;
		img = "2";
		name = "死亡之翼";
	}
	
	public void cardEffect() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				p.recover(1);
			} else {
				p.hurt(1);
			}
		}
	}
}
