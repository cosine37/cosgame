package com.cosine.cosgame.gardenwar.baseold;

import java.util.List;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;
import com.cosine.cosgame.gardenwar.Player;

public class Garlic extends Card{
	public Garlic() {
		name = "大蒜";
		img = "garlic";
		cost = 5;
		level = 1;
		shield = 4;
		type = Consts.EQUIP;
		activatable = true;
		addClan(Consts.DIRT);
		desc = "你可以将一张手牌置于下一名玩家的弃牌堆，若如此做，本植物受到1点伤害。";
	}
	
	public void activate() {
		player.setAskType(Consts.CHOOSEONEHAND);
		player.setAskMsg("请选择一张牌交给下家");
		player.setAskTargets(this);
	}
	
	public void resolve(List<Integer> targets) {
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x>=0 && x<player.getHand().size()) {
				Card c = player.getHand().remove(x);
				Player p = player.nextPlayer();
				p.getDiscard().add(c);
				hp--;
				if (hp<=0) {
					player.removeFromEquip(this);
				}
				activated = true;
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
