package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

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
				if (p.getHp() == PokewhatConsts.MAXHP) {
					board.getLogger().logOverHeal(p);
				} else {
					p.recover(1);
					board.getLogger().logHeal(p, 1);
				}
			} else {
				p.hurt(1);
				board.getLogger().logOn(player, p, 1);
			}
		}
	}
}
