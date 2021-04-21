package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Status;

public class Plague extends Status{
	public Plague() {
		super();
		num = Consts.PLAGUE;
		name = "瘟疫";
		img = "s04";
	}
	
	public boolean votedThis(boolean f) {
		return false;
	}
}
