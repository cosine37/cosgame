package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardSuccessBook extends Card {
	public CardSuccessBook() {
		super();
		id = 9000;
		name = "《成功学》";
		desc = "“加油，奥利给！”";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		
	}
	
	public boolean playable() {
		return false;
	}
}
