package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Spikerock extends Card{
	public Spikerock() {
		name = "铁荆棘";
		img = "spikerock";
		cost = 8;
		level = 2;
		hasStartTurn = true;
		shield = 8;
		type = Consts.EQUIP;
		addClan(Consts.WOOD);
		addClan(Consts.DIRT);
		desc = "回合开始获得p4。";
	}
	
	public void startTurn() {
		player.addAtk(4);
	}
}
