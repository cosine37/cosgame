package com.cosine.cosgame.gardenwar.base;

import java.util.List;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class CabbagePult extends Card{
	public CabbagePult() {
		name = "卷心菜投手";
		img = "cabbagePult";
		cost = 4;
		type = Consts.CARD;
		addClan(Consts.WOOD);
		addClan(Consts.CATAPULT);
		desc = "你可以消耗一张非僵尸手牌，若如此做，获得p2。";
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
			player.setAskMsg("你可以消耗一张非僵尸手牌。");
		}
		
	}
	
	public void resolve(List<Integer> targets) {
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x!=-1 && player.getHand().get(x).isTrashable() && player.getHand().get(x).getType() != Consts.ZOMBIE) {
				player.trash(x);
				player.addAtk(2);
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
