package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class WaterStreet extends Card{
	public WaterStreet() {
		super();
		name = "长荡湖水街";
		cost = 5;
		img = "p518";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public String canUseSkill() {
		if (this.beautifyLevel < 3) {
			return "y";
		} else {
			return "n";
		}
	}
	
	public String canUseSkillSameRound() {
		if (this.beautifyLevel < 3) {
			return "y";
		} else {
			return "n";
		}
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		ask = useSkill(index, x);
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 长荡湖水街 的特效。");
		if (player.getCoin() >= 1 && this.beautifyLevel < 3) {
			int c = player.getCoin()-1;
			player.setCoin(c);
			this.beautify();
			player.draw(1);
			board.log(name + "摸了1张牌。");
			board.log(name + "的 长荡湖水街 的装饰等级提升了。");
		} else {
			board.log("然而，穷鬼" + name + "拿不出1￥，所以无事发生。");
		}
		return ask;
	}
}
