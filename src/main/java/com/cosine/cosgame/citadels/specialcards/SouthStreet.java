package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class SouthStreet extends Card{
	public SouthStreet() {
		super();
		name = "南大街";
		cost = 5;
		img = "p502";
		color = CitadelsConsts.PURPLE;
	}
	
	public String canUseSkill() {
		return "y";
	}
	
	public String canUseSkillSameRound() {
		return "y";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		if (player.getHand().size() > 1) {
			ask.setAskId(99502);
			ask.setAskType(5);
			ask.setAskBuiltIndex(index);
			ask.setMsg("请弃置一张手牌");
		} else {
			ask = useSkill(index, x);
		}
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 南大街 的特效。");
		if (player.getHand().size() == 0) {
			board.log("然而" + name + "没有手牌，所以无事发生。");
		} else  {
			if (player.getHand().size() == 1) {
				x = 0;
			}
			Card c = player.getHand().remove(x);
			board.bottomDeck(c);
			player.addCoin(1);
			board.log(name + "弃置一张手牌，得到了1￥。");
		}
		return ask;
	}
}
