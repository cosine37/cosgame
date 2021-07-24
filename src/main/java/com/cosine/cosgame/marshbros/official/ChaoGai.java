package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Player;

public class ChaoGai extends Card {
	public ChaoGai() {
		super();
		nickname = "托塔天王";
		name = "晁盖";
		img = "ChaoGai";
		atk = 3;
		hp = 2;
	}
	
	public void lastWish() {
		Player p = role.getPlayer();
		p.draw(1);
	}
}
