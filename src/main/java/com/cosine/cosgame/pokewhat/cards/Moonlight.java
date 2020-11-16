package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class Moonlight extends Card{
	public Moonlight() {
		num = 3;
		img = "3";
		name = "月光";
	}
	
	public void cardEffect() {
		Random rand = new Random();
		int x = rand.nextInt(3) + 1;
		if (x > PokewhatConsts.MAXHP - player.getHp()) {
			x = PokewhatConsts.MAXHP - player.getHp();
		}
		if (player.getHp() == PokewhatConsts.MAXHP) {
			board.getLogger().logOverHeal(player);
		} else {
			player.recover(x);
			board.getLogger().logHeal(player, x);
		}
		
	}
}
