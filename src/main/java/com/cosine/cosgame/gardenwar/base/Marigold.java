package com.cosine.cosgame.gardenwar.base;

import java.util.Random;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Marigold extends Card{
	public Marigold() {
		name = "金盏花";
		img = "marigold";
		cost = 3;
		hasStartTurn = true;
		shield = 4;
		type = Consts.EQUIP;
		addClan(Consts.FLOWER);
		desc = "回合开始随机获得s1~s3。";
	}
	
	public void startTurn() {
		int[] sunValues = {1,1,1,2,2,3};
		Random rand = new Random();
		int n = sunValues[rand.nextInt(6)];
		player.addSun(n);
	}
}
