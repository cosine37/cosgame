package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CanalNo5 extends Card{
	public CanalNo5() {
		super();
		name = "运河五号";
		cost = 5;
		img = "p511";
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
		board.log(name + "发动了建筑 运河五号 的特效。");
		if (player.getCoin() == 0) {
			board.log("然而，穷鬼" + name + "拿不出1￥，所以无事发生。");
		} else if (beautifyLevel < 3){
			int c = player.getCoin()-1;
			player.setCoin(c);
			beautifyLevel++;
			board.log(name + "花费了1￥装饰了 运河五号。");
		}
		return ask;
	}
	
	public void alterPlayerAbility() {
		super.alterPlayerAbility();
		int x = player.getCostReducers().get(CitadelsConsts.PURPLE);
		x = x+beautifyLevel;
		player.getCostReducers().set(CitadelsConsts.PURPLE, x);
	}
}
