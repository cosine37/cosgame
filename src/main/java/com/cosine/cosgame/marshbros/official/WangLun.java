package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Player;

public class WangLun extends Card {
	public WangLun() {
		super();
		nickname = "白衣秀士";
		name = "王伦";
		img = "WangLun";
		atk = 3;
		hp = 2;
	}
	
	public void lastWish() {
		Player p = role.getPlayer();
		p.draw(1);
	}
}
