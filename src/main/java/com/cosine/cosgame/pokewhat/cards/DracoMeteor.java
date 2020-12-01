package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class DracoMeteor extends Card{
	public DracoMeteor() {
		super();
		num = 1;
		img = "1";
		name = "龙星群";
	}
	
	public void cardEffect() {
		int i,x;
		Random rand = new Random();
		x = rand.nextInt(3) + 1;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				
			} else {
				p.hurt(x);
				board.getLogger().logOn(player, p, x);
			}
		}
	}
	
	public void penalty() {
		Random rand = new Random();
		int x = rand.nextInt(3) + 1;
		player.hurt(x);
		board.getLogger().logMiss(player, this, x);
	}
}
