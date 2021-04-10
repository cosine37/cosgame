package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Status;

public class NoStatus extends Status{
	public NoStatus() {
		super();
		num = Consts.NOSTATUS;
		name = "没有";
		img = "s00";
	}
}
