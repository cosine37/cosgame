package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardSubstitute extends Card {
	public CardSubstitute() {
		super();
		id = 44;
		name = "替身卡";
		desc = "当你即将入狱时，消耗一张该牌，清空通缉值并抵消入狱的效果。";
		rarity = 2;
		passive();
	}
	
	public boolean playable() {
		return false;
	}
	
	public boolean clearJail() {
		return true;
	}
}
