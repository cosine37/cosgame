package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Threepeater extends Card{
	public Threepeater() {
		name = "三重豌豆";
		img = "threepeater";
		level = 1;
		cost = 6;
		type = Consts.CARD;
		addClan(Consts.PEA);
		desc = "将3张豌豆射手加入你的手牌。";
	}
	
	public void play() {
		super.play();
		for (int i=0;i<3;i++) {
			PeaShooter peaShooter = new PeaShooter();
			player.getHand().add(peaShooter);
		}
		board.log(player.getName() + "将3张豌豆射手加入了手牌。");
	}
}
