package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class LightCity extends Card{
	public LightCity() {
		super();
		name = "邹区灯具城";
		cost = 6;
		img = "p616";
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
		board.log(name + "发动了建筑 邹区灯具城 的特效。");
		if (player.getCoin() >= 1 && this.beautifyLevel < 3) {
			int c = player.getCoin()-1;
			player.setCoin(c);
			this.beautify();
			board.log(name + "的 邹区灯具城 的装饰等级提升了。");
		} else {
			board.log("然而，穷鬼" + name + "拿不出1￥，所以无事发生。");
		}
		return ask;
	}
	
	public int getExtraScore() {
		int ans = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			Card c = player.getBuilt().get(i);
			ans = ans + c.getBeautifyLevel();
		}
		return ans;
	}
}
