package com.cosine.cosgame.marshbros.official;

import com.cosine.cosgame.marshbros.Card;
import com.cosine.cosgame.marshbros.Consts;
import com.cosine.cosgame.marshbros.Player;

public class Tiger extends Card {
	public Tiger() {
		super();
		nickname = "吊睛白额";
		name = "大虫";
		img = "Tiger";
		atk = 2;
		hp = 1;
		subType = Consts.BEAST;
	}
	
	public void lastWish() {
		Player p = role.getPlayer();
		p.draw(2);
	}
}
