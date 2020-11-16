package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class LifeDew extends Card{
	public LifeDew() {
		num = 8;
		img = "8";
		name = "生命水滴";
	}
	
	public void cardEffect() {
		if (player.getHp() == PokewhatConsts.MAXHP) {
			board.getLogger().logOverHeal(player);
		} else {
			player.recover(1);
			board.getLogger().logHeal(player, 1);
		}
	}
}
