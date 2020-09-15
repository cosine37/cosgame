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
		for (int i=0;i<player.getHand().size();i++) {
			if (player.notIdentical(player.getHand().get(i))) {
				if (player.getHand().get(i).canBuildWhen(player.getBuilt().size()-1)) {
					canBuild = true;
					break;
				}
			}
		}
		if (player.getHand().size() > 1 && player.getNumBuilt() < player.getBuildLimit() && canBuild) {
			List<String> ls = new ArrayList<>();
			for (int i=0;i<player.getHand().size();i++) {
				if (player.notIdentical(player.getHand().get(i))) {
					if (player.getHand().get(i).canBuildWhen(player.getBuilt().size()-1)) {
						ls.add("y");
					} else {
						ls.add("n");
					}
				} else {
					ls.add("n");
				}
			}
			
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
			if (player.notIdentical(player.getHand().get(i))) {
				canBuild = true;
				break;
			}
		}
		
		if (canBuild == false) {
			board.log("然而" + name + "没有可以建造的建筑，所以无事发生。");
		} else if (player.getNumBuilt() >= player.getBuildLimit()) {
			board.log("然而" + name + "没有可以建造的建筑，所以无事发生。");
		}
		
		else  {
			if (player.getHand().size() == 1) {
				x = 0;
			}
			Card f = player.getBuilt().remove(index);
			Card c = player.getHand().remove(x);
			board.bottomDeck(f);
			player.getBuilt().add(c);
			int y = player.getNumBuilt()+1;
			player.setNumBuilt(y);
			board.log(name + "通过拆除 脚手架 免费建造了 " + c.getName() + " 。");
		}
		return ask;
	}
}
