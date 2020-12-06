package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class LifeDew extends Card{
	public LifeDew() {
		super();
		num = 8;
		img = "8";
		name = "生命水滴";
	}
	
	public Animation getMoveAnimation() {
		animation = new Animation();
		animation.addFrame(player.getIndex(), PokewhatConsts.MOVE, 500, "a08");
		return animation;
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
