package com.cosine.cosgame.onenight;

import com.cosine.cosgame.onenight.statuses.*;

public class StatusFactory {
	public static Status createStatus(int statusNum) {
		Status status = new Status();
		if (statusNum == Consts.NOSTATUS) {
			status = new NoStatus();
		} else if (statusNum == Consts.UNKNOWN){
			status = new Unknown();
		} else if (statusNum == Consts.LOVE) {
			status = new Love();
		} else if (statusNum == Consts.STONED) {
			status = new Stoned();
		} else if (statusNum == Consts.CONFUSED) {
			status = new Confused();
		}
		
		return status;
	}
}
