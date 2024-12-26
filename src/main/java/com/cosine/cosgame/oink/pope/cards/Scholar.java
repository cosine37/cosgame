package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.CardFactory;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Scholar extends Card{
	public Scholar() {
		super();
		this.num = 6;
		this.name = "学者";
		this.img = "Scholar";
		this.desc = "抽3张牌（不足则全抽），将你的手牌放回至牌堆直至你只有一张手牌。";
		this.color = "Magenta";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay();
		player.setPhase(Consts.RETURNCARDS);
		int x = game.getDeck().size();
		if (x>3) x=3;
		player.draw(x);
		log(player.getName() + "抽了" + Integer.toString(x) + "张牌");
	}
	
	public void onResolve(int val) {
		if (player.getPhase() == Consts.RETURNCARDS) {
			int x = val;
			if (x>=0 && x<player.getHand().size()) {
				Card c = player.getHand().remove(x);
				game.getDeck().add(0,c);
				log(player.getName() + "将一张牌放回了牌堆顶");
				if (player.getHand().size()==1) {
					player.setPhase(Consts.OFFTURN);
				}
			}
		}
	}
	
}
