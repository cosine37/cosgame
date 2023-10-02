package com.cosine.cosgame.gardenwar.baseold;

import java.util.List;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class MelonPult extends Card{
	public MelonPult() {
		name = "西瓜投手";
		img = "melonPult";
		level = 2;
		cost = 7;
		type = Consts.CARD;
		addClan(Consts.FRUIT);
		addClan(Consts.CATAPULT);
		desc = "你可以消耗一张非僵尸手牌，若如此做，获得p6。";
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
			player.setAskTargets(this);
		}
		
	}
	
	public void resolve(List<Integer> targets) {
		if (targets.size() > 0) {
			int x = targets.get(0);
			
			if (x!=-1 && player.getHand().get(x).isTrashable() && player.getHand().get(x).getType() != Consts.ZOMBIE) {
				player.trash(x);
				player.addAtk(6);
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
