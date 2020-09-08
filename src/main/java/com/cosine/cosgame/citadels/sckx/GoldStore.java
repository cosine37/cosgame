package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class GoldStore extends Card{
	public GoldStore() {
		super();
		name = "常州金店";
		cost = 3;
		img = "p304";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		int x;
		if (player.getCoin() > 3) {
			x = 3;
		} else {
			x = player.getCoin();
		}
		this.beautifyLevel = x;
		int y = player.getCoin() -x;
		player.setCoin(y);
		board.log(player.getName() + "用" + Integer.toString(x) + "￥装饰了 常州金店。");
	}
	
	public String canUseSkill() {
		if (this.beautifyLevel > 0) {
			return "y";
		} else {
			return "n";
		}
	}
	
	public String canUseSkillSameRound() {
		return "n";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		ask = useSkill(index, x);
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 常州金店 的特效。");
		if (beautifyLevel > 0) {
			beautifyLevel--;
			int c = player.getCoin()+1;
			player.setCoin(c);
			board.log(name + "从 常州金店 中获得了1￥。");
		}
		return ask;
	}
}
