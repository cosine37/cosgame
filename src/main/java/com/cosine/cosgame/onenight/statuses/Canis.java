package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Status;

public class Canis extends Status{
	public Canis() {
		super();
		num = Consts.CANIS;
		name = "狼种";
		img = "s06";
	}
	
	public int getSide(int x) {
		return Consts.WOLF;
	}
}
