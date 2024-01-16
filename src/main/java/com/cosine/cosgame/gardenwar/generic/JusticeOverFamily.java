package com.cosine.cosgame.gardenwar.generic;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class JusticeOverFamily extends Card{
	public JusticeOverFamily() {
		name = "大义灭亲";
		img = "justiceOverFamily";
		cost = 4;
		type = Consts.CARD;
		addClan(Consts.ITEM);
		desc = "消耗一张手牌，若如此做，获得a4。";
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
				player.addAtk(4);
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
