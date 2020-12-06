package com.cosine.cosgame.pokewhat.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class Eternalbeam extends Card{
	public Eternalbeam() {
		super();
		num = 0;
		img = "0";
		name = "无极光束";
	}
	
	public Animation getMoveAnimation() {
		animation = new Animation();
		int i;
		List<Integer> targets = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			if (i != player.getIndex()) {
				targets.add(i);
			}
		}
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_1");
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_2");
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_1");
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_2");
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_1");
		animation.addFrame(targets, PokewhatConsts.MOVE, 300, "a00_2");
		animation.addFrame(targets, PokewhatConsts.MOVE, 200, "a00_3");
		animation.addFrame(targets, PokewhatConsts.MOVE, 200, "a00_4");
		animation.addFrame(targets, PokewhatConsts.MOVE, 200, "a00_5");
		animation.addFrame(targets, PokewhatConsts.MOVE, 200, "a00_6");
		return animation;
	}
	
	public void cardEffect() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				
			} else {
				p.hurt(6);
				System.out.println(p.getHp());
				board.getLogger().logOn(player, p, 6);
			}
		}
	}
}
