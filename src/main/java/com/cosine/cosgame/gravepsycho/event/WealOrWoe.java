package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class WealOrWoe extends Event{
	public WealOrWoe() {
		super();
		this.num = 1;
		this.img = "wealorwoe";
		this.name = "福祸相依";
		this.desc = "当一张灾难牌被亮出时，依然在探险的玩家将先平分5枚钱币（但灾难牌依然有效）。";
	}
	
	public void onReveal(Card c) {
		if (c.getType() == Consts.DISASTER) {
			board.distributeCoins(5);
			board.getLogger().log("福祸相依！灾难接近的同时，5枚钱币被发现了");
		}
	}
}
