package com.cosine.cosgame.gardenwar.generic;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Sacrifice extends Card{
	public Sacrifice() {
		name = "祭祀";
		img = "sacrifice";
		cost = 2;
		type = Consts.CARD;
		addClan(Consts.ITEM);
		desc = "你可以消耗一张手牌，若如此做，随机获得c1~c3。";
	}
	
	public void play() {
		super.play();
		boolean needAsk = false;
		int i;
		for (i=0;i<player.getHand().size();i++) {
			Card c = player.getHand().get(i);
			if (c.isTrashable() && c.getType()!=Consts.ZOMBIE) {
				needAsk = true;
				break;
			}
		}
		//System.out.println(needAsk);
		if (needAsk) {
			player.setAskType(Consts.CHOOSEONEHANDNONZOMBIE);
			player.setAskSubType(Consts.NONE);
			player.setAskMsg("你可以消耗一张手牌。");
			player.setAskTargets(this);
		}
		
	}
	
	public void resolve(List<Integer> targets) {
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x!=-1 && player.getHand().get(x).isTrashable() && player.getHand().get(x).getType() != Consts.ZOMBIE) {
				player.trash(x);
				int[] sunValues = {1,2,2,2,3,3};
				Random rand = new Random();
				int n = rand.nextInt(sunValues.length);
				player.addSun(sunValues[n]);
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
