package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Status;

public class Stoned extends Status{
	public Stoned() {
		super();
		num = Consts.STONED;
		name = "石化";
		img = "s02";
	}
	
	public void earlyNightOperation() {
		player.setStoned(true);
	}
}
