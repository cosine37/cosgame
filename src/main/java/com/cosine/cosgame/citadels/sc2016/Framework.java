package com.cosine.cosgame.citadels.sc2016;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Framework extends Card{
	public Framework() {
		super();
		name = "脚手架";
		cost = 3;
		img = "p305";
		color = CitadelsConsts.PURPLE;
		expansion = 2;
	}
	
	public String canUseSkill() {
		return "y";
	}
	
	public String canUseSkillSameRound() {
		return "y";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		boolean canBuild = false;
		List<String> ls = new ArrayList<>();
		for (int i=0;i<player.getHand().size();i++) {
			if (player.canBuildFree(i)) {
				ls.add("y");
				canBuild = true;
			} else {
				ls.add("n");
			}
		}
		if (canBuild) {
			ask.setAskId(99305);
			ask.setAskType(5);
			ask.setAskBuiltIndex(index);
			ask.setMsg("你可以拆除 脚手架 并免费建造一张手牌。");
			ask.setLs(ls);
		} else {
			ask = useSkill(index, x);
		}
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 脚手架 的特效。");
		boolean canBuild = false;
		
		for (int i=0;i<player.getHand().size();i++) {
			if (player.canBuildFree(i)){
				canBuild = true;
				break;
			}
		}
		
		if (canBuild == false) {
			board.log("然而" + name + "没有可以建造的建筑，所以无事发生。");
		}
		
		else  {
			if (player.getHand().size() == 1) {
				x = 0;
			}
			Card f = player.getBuilt().remove(index);
			Card c = player.getHand().get(x);
			board.log(name + "通过拆除 脚手架 免费建造了 " + c.getName() + " 。");
			board.bottomDeck(f);
			player.freeBuild(x);
		}
		return ask;
	}
}
