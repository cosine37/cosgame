package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardHealthInsurance extends Card {
	public CardHealthInsurance() {
		super();
		id = 25;
		name = "医保卡";
		desc = "你每次住院的费用减免$500。";
		rarity = 2;
		passive();
	}
	
	public boolean playable() {
		return false;
	}
	
	public int wardFeeDeduction() {return 500;}
}
