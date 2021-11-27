package com.cosine.cosgame.gardenwar.base;

import java.util.List;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class CoffeeBean extends Card{
	public CoffeeBean() {
		name = "咖啡豆";
		img = "coffeeBean";
		cost = 5;
		level = 1;
		type = Consts.CARD;
		addClan(Consts.SEED);
		desc = "指定一张手牌中的阳光菇，其本局游戏额外生成s1（上限为s3）。";
	}
	
	boolean canBeSelected(Card c) {
		boolean ans = false;
		if (c.getImg().contentEquals("sunShroom") || c.getImg().contentEquals("sunShroom2")) {
			if (c.getExtraBits() == null || c.getExtraBits().size() == 0) {
				ans = true;
			} else if (c.getExtraBits().get(0) == 1 || c.getExtraBits().get(0) == 2) {
				ans = true;
			}
		}
		return ans;
	}
	
	public void play() {
		super.play();
		boolean needAsk = false;
		int i;
		for (i=0;i<player.getHand().size();i++) {
			Card c = player.getHand().get(i);
			if (canBeSelected(c)) {
				needAsk = true;
				break;
			}
		}
		//System.out.println(needAsk);
		if (needAsk) {
			player.setAskType(Consts.CHOOSESUNSHROOM);
			player.setAskSubType(Consts.NONE);
			player.setAskMsg("你可以升级一张阳光菇。");
			player.setAskTargets(this);
		}
		
	}
	
	public void resolve(List<Integer> targets) {
		if (targets.size() > 0) {
			int x = targets.get(0);
			if (x!=-1 && canBeSelected(player.getHand().get(x))) {
				Card c = player.getHand().get(x);
				if (c.getExtraBits().size() == 0) {
					c.getExtraBits().add(2);
				} else {
					int y = c.getExtraBits().get(0);
					if (y < 3) {
						y++;
						c.getExtraBits().set(0, y);
					}
				}
			}
		} else {
			
		}
		player.emptyAsk();
	}
}
