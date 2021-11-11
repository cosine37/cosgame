package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Spikeweed extends Card{
	public Spikeweed() {
		name = "荆棘草";
		img = "spikeweed";
		cost = 4;
		hasStartTurn = true;
		shield = 6;
		type = Consts.EQUIP;
		addClan(Consts.WOOD);
		addClan(Consts.DIRT);
		desc = "回合开始获得p2。";
	}
	
	public void startTurn() {
		player.addAtk(2);
	}
}
