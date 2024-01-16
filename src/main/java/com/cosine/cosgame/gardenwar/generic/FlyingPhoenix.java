package com.cosine.cosgame.gardenwar.generic;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class FlyingPhoenix extends Card{
	public FlyingPhoenix() {
		name = "凤凰于飞";
		img = "flyingPhoenix";
		cost = 5;
		type = Consts.CARD;
		addClan(Consts.ACTION);
		desc = "+2抽牌。";
	}
	
	public void play() {
		super.play();
		player.draw(2);
	}
}
