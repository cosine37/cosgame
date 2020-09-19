package com.cosine.cosgame.citadels.sckx.omnicolor;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class NewsCenter extends Card {
	public NewsCenter() {
		super();
		name = "新闻大厦";
		cost = 4;
		img = "o43y";
		color = CitadelsConsts.YELLOW;
		expansion = 3;
	}
	
	public void afterTakeActionEffect() {
		String name = player.getName();
		Card c = board.getDeck().remove(0);
		cardsUnder.add(c);
		board.log(name + "将牌堆顶的一张牌放置于新闻大厦下方。");
	}
	
	public void endTurnEffect() {
		if (cardsUnder.size() >= 2) {
			for (int i=cardsUnder.size()-1;i>=0;i--) {
				Card c =cardsUnder.remove(i);
				player.addToHand(c);
			}
			board.log(player.getName() + "将新闻大厦下方的牌收为手牌。");
			board.log(player.getName() + "的新闻大厦变更为特殊类型。");
			img = "o43p";
			color = CitadelsConsts.PURPLE;
			cost = 3;
		}
	}
	
}
