package com.cosine.cosgame.pokewhat.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.pokewhat.Animation;
import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;
import com.cosine.cosgame.pokewhat.PokewhatConsts;

public class DracoMeteor extends Card{
	public DracoMeteor() {
		super();
		num = 1;
		img = "1";
		name = "龙星群";
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
		animation.addFrame(targets, PokewhatConsts.MOVE, 250, "a01_1");
		animation.addFrame(targets, PokewhatConsts.MOVE, 250, "a01_2");
		animation.addFrame(targets, PokewhatConsts.MOVE, 250, "a01_3");
		animation.addFrame(targets, PokewhatConsts.MOVE, 250, "a01_4");
		return animation;
	}
	
	public Animation getMissAnimation() {
		animation = new Animation();
		animation.addFrame(player.getIndex(), PokewhatConsts.MOVE, 600, "a01_4");
		return animation;
	}
	
	public void cardEffect() {
		int i,x;
		Random rand = new Random();
		x = rand.nextInt(3) + 1;
		if (x==1) x=2;
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
