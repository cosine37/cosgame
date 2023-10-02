package com.cosine.cosgame.gardenwar.baseold;

import com.cosine.cosgame.gardenwar.Card;
import com.cosine.cosgame.gardenwar.Consts;

public class Starfruit extends Card{
	public Starfruit() {
		name = "杨桃";
		img = "starfruit";
		cost = 5;
		level = 1;
		type = Consts.CARD;
		addClan(Consts.FRUIT);
		autoplay = true;
		desc = "你牌库中每有5张牌，获得p1";
	}
	
	public void play() {
		super.play();
		int n = player.numCards() / 5;
		player.addAtk(n);
	}
	
	public String getDesc() {
		String ans = desc;
		if (player != null) {
			int n = player.numCards() / 5;
			ans = ans + "（获得p" + n + "）。";
		} else {
			ans = ans + "。";
		}
		return ans;
	}
}
