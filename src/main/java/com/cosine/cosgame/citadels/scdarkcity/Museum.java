package com.cosine.cosgame.citadels.scdarkcity;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Museum extends Card {
	public Museum() {
		super();
		name = "博物馆";
		cost = 4;
		img = "p409";
		color = CitadelsConsts.PURPLE;
		expansion = 1;
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
			List<String> ls = new ArrayList<>();
			for (int i=0;i<player.getHand().size();i++) {
				ls.add("y");
			}
			ask.setAskId(99502);
			ask.setAskType(5);
			ask.setAskBuiltIndex(index);
			ask.setMsg("请将一张手牌置于 博物馆 内。");
			ask.setLs(ls);
		} else {
			ask = useSkill(index, x);
		}
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 博物馆 的特效。");
		if (player.getHand().size() == 0) {
			board.log("然而" + name + "没有手牌，所以无事发生。");
		} else  {
			if (player.getHand().size() == 1) {
				x = 0;
			}
			Card c = player.getHand().remove(x);
			Card m = player.getBuilt().get(index);
			m.getCardsUnder().add(c);
			board.log(name + "将一张手牌放入了 博物馆 内。");
		}
		return ask;
	}
	
	public int getExtraScore() {
		int ans = player.getBuilt().get(index).getCardsUnder().size();
		return ans;
	}
}
