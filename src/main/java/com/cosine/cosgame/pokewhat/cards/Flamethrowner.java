package com.cosine.cosgame.pokewhat.cards;

import java.util.List;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class Flamethrowner extends Card{
	public Flamethrowner() {
		super();
		num = 7;
		img = "7";
		name = "喷射火焰";
	}
	
	public Animation getMoveAnimation() {
		animation = new Animation();
		animation.addFrame(player.prevPlayerIndex(), PokewhatConsts.MOVE, 500, "a07");
		return animation;
	}
	
	public void cardEffect() {
		Player p = player.prevPlayer();
		p.hurt(1);
		board.getLogger().logOn(player, p, 1);
	}
}
