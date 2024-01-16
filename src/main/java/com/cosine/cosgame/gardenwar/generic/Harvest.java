package com.cosine.cosgame.gardenwar.generic;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Harvest extends Card{
	public Harvest() {
		name = "五谷丰登";
		img = "harvest";
		cost = 5;
		sun = 2;
		type = Consts.CARD;
		addClan(Consts.ACTION);
		desc = "+1抽牌。";
	}
	
	public void play() {
		super.play();
		player.draw(1);
	}
}
