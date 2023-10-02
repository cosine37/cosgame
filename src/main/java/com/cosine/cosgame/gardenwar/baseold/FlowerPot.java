package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class FlowerPot extends Card{
	public FlowerPot() {
		name = "花盆";
		img = "flowerPot";
		cost = 1;
		type = Consts.CARD;
		addClan(Consts.DIRT);
		desc = "你的出牌区和放置区每有1张花朵，获得s1";
	}
	
	public void play() {
		super.play();
		int numFlower = 0;
		int i;
		for (i=0;i<player.getPlay().size();i++) {
			if (player.getPlay().get(i).isClan(Consts.FLOWER)) {
				numFlower++;
			}
		}
		for (i=0;i<player.getEquip().size();i++) {
			if (player.getEquip().get(i).isClan(Consts.FLOWER)) {
				numFlower++;
			}
		}
		player.addSun(numFlower);
	}
	
	public String getDesc() {
		String ans = desc;
		if (player != null) {
			int numFlower = 0;
			int i;
			for (i=0;i<player.getPlay().size();i++) {
				if (player.getPlay().get(i).isClan(Consts.FLOWER)) {
					numFlower++;
				}
			}
			for (i=0;i<player.getEquip().size();i++) {
				if (player.getEquip().get(i).isClan(Consts.FLOWER)) {
					numFlower++;
				}
			}
			ans = ans + "（获得s" + numFlower + "）。";
		} else {
			ans = ans + "。";
		}
		return ans;
	}
}
