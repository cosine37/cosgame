package com.cosine.cosgame.gardenwar;

import com.cosine.cosgame.gardenwar.base.*;

public class AllRes {
	public static Card getBasic(int x) {
		Card c = null;
		if (x == 2) {
			c = new PuffShroom();
		} else if (x == 3) {
			c = new SunShroom();
		} else if (x == 0) {
			c = new PeaShooter();
		} else if (x == 1) {
			c = new Sunflower();
		}
		return c;
	}
}
