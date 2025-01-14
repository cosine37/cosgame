package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Impatient extends Event{
	public Impatient() {
		super();
		this.num = 6;
		this.img = "impatient";
		this.name = "急不可耐";
		this.desc = "每次玩家决策后，会连续翻出2张牌且都会结算。";
	}
	
	public boolean revealNext() {
		board.revealACard();
		board.revealACard();
		return true;
	}
}
