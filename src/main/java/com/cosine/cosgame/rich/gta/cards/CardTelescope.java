package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardTelescope extends Card {
	public CardTelescope() {
		super();
		id = 45;
		name = "望远镜";
		desc = "你所有牌的成功率+10%。多个望远镜的效果不会叠加。";
		rarity = 2;
		passive();
	}
	
	public boolean playable() {
		return false;
	}
	
	public boolean passiveAimBoost() {
		return true;
	}
}
