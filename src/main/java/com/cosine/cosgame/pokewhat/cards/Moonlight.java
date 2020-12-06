package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class Moonlight extends Card{
	public Moonlight() {
		super();
		num = 3;
		img = "3";
		name = "月光";
	}
	
	public Animation getMoveAnimation() {
		animation = new Animation();
		animation.addFrame(player.getIndex(), PokewhatConsts.MOVE, 500, "a03");
		return animation;
	}
	
	public void cardEffect() {
		Random rand = new Random();
		int x = rand.nextInt(3) + 1;
		if (x == 1) x=2;
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
