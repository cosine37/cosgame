package com.cosine.cosgame.citadels.specialcards;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Subway extends Card{
	public Subway() {
		super();
		name = "地铁";
		cost = 5;
		img = "p501";
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
		ask = useSkill(index, x);
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 地铁 的特效。");
		if (player.getCoin() >= 2) {
			int c = player.getCoin()-2;
			player.setCoin(c);
			player.draw(3);
			board.log(name + "花费了2￥摸了3张牌。");
		} else {
			board.log("然而，穷鬼" + name + "拿不出2￥，所以无事发生。");
		}
		return ask;
	}
}
