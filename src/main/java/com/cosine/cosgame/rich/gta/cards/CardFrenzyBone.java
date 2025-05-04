package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardFrenzyBone extends Card {
	public CardFrenzyBone() {
		super();
		id = 70;
		name = "狂骨卡";
		desc = "当你造成伤害时，你获得+1healthP。住院时消耗该牌。";
		rarity = 2;
		passive();
	}
	
	public boolean exhaustOnWard() {return true;}
	
	public void onHurt(int x) {
		board.getLogger().log(player.getName() + " 的 " + name + " 效果发动");
		player.addHp(1);
	}
	
}
