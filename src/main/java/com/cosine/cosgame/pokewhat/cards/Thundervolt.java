package com.cosine.cosgame.pokewhat.cards;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class Thundervolt extends Card{
	public Thundervolt() {
		super();
		num = 5;
		img = "5";
		name = "十万伏特";
	}
	
	public Animation getMoveAnimation() {
		int i1 = player.prevPlayerIndex();
		int i2 = player.nextPlayerIndex();
		if (i1 == i2) {
			animation = new Animation();
			animation.addFrame(i1, PokewhatConsts.MOVE, 500, "a05");
		} else {
			animation = new Animation();
			List<Integer> targets = new ArrayList<>();
			targets.add(i1);
			targets.add(i2);
			animation.addFrame(targets, PokewhatConsts.MOVE, 500, "a05");
		}
		
		return animation;
	}
	
	public void cardEffect() {
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		p1.hurt(1);
		board.getLogger().logOn(player, p1, 1);
		if (!p1.getName().contentEquals(p2.getName())) {
			p2.hurt(1);
			board.getLogger().logOn(player, p2, 1);
		}
	}
}
