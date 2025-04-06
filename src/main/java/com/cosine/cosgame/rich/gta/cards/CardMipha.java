package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardMipha extends Card {
	public CardMipha() {
		super();
		id = 50;
		name = "米法卡";
		desc = "当你即将住院时，消耗一张该牌，回复所有生命值并抵消住院的效果。";
		rarity = 3;
		passive();
	}
	
	public boolean playable() {
		return false;
	}
	
	public boolean clearWard() {
		return true;
	}
}
