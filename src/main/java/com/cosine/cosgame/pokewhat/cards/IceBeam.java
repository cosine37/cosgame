package com.cosine.cosgame.pokewhat.cards;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class IceBeam extends Card{
	public IceBeam() {
		super();
		num = 6;
		img = "6";
		name = "冰冻光束";
	}

	public Animation getMoveAnimation() {
		animation = new Animation();
		animation.addFrame(player.nextPlayerIndex(), PokewhatConsts.MOVE, 500, "a06");
		return animation;
	}
	
	public void cardEffect() {
		Player p = player.nextPlayer();
		p.hurt(1);
		board.getLogger().logOn(player, p, 1);
	}
}
