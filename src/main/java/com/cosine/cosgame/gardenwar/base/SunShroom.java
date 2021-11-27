package com.cosine.cosgame.gardenwar.base;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class SunShroom extends Card{
	public SunShroom() {
		name = "阳光菇";
		img = "sunShroom";
		cost = 0;
		type = Consts.CARD;
		sun = 1;
		autoplay = true;
		addClan(Consts.MUSHROOM);
	}
	
	public String getImg() {
		if (extraBits.size()>0) {
			int x = extraBits.get(0);
			if (x==2) {
				return "sunShroom2";
			} else if (x==3) {
				return "sunShroom3";
			}
		}
		return img;
	}
	
	public int getSun() {
		if (extraBits.size()>0) {
			int x = extraBits.get(0);
			if (x==2) {
				return 2;
			} else if (x==3) {
				return 3;
			}
		}
		return sun;
	}
}
