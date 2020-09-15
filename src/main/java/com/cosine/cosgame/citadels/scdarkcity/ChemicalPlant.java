package com.cosine.cosgame.citadels.scdarkcity;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;

public class ChemicalPlant extends Card {
	public ChemicalPlant() {
		super();
		name = "化工厂";
		cost = 3;
		img = "p306";
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
		int i;
		boolean flag = true;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			int builtSize = 0;
			for (int j=0;j<p.getBuilt().size();j++) {
				builtSize = builtSize + p.getBuilt().get(j).buildCount();
			}
			if (player.getName().contentEquals(p.getName())) {
				continue;
			}
			if (builtSize >= board.getFinishCount()) {
				continue;
			}
			if (p.getBuilt().size() > 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			ask = useSkill(index, x);
		} else {
			ask.setAskId(99306);
			ask.setAskType(CitadelsConsts.CHOOSEBUILT);
			ask.setMsg("请选择一个你要摧毁的建筑。");
			List<List<String>> builtInfo = new ArrayList<>();
			int j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				List<String> singleBuiltInfo = new ArrayList<>();
				int builtSize = 0;
				for (j=0;j<p.getBuilt().size();j++) {
					builtSize = builtSize + p.getBuilt().get(i).buildCount();
				}
				for (j=0;j<p.getBuilt().size();j++) {
					if (builtSize >= board.getFinishCount()) {
						singleBuiltInfo.add("-1");
					} else if (player.getName().contentEquals(p.getName())) {
						singleBuiltInfo.add("-1");
					} else {
						singleBuiltInfo.add("0");
					}
				}
				builtInfo.add(singleBuiltInfo);
			}
			ask.setBuiltInfo(builtInfo);
		}
		
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		boolean flag = true;
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (player.getName().contentEquals(p.getName())) {
				continue;
			}
			int builtSize = 0;
			for (int j=0;j<p.getBuilt().size();j++) {
				builtSize = builtSize + p.getBuilt().get(j).buildCount();
			}
			if (builtSize >= board.getFinishCount()) {
				continue;
			}
			if (p.getBuilt().size() > 0) {
				flag = false;
				break;
			}
		}
		if (flag) {
			board.log(name + "发动了建筑 化工厂 的特效。");
			board.log("然而没有可以炸毁的建筑，所以无事发生。");
		} else {
			board.log(name + "引爆了 化工厂。");
			int temp = x%10000;
			int playerIndex = temp/100;
			int builtIndex = temp%100;
			
			Player victim = board.getPlayers().get(playerIndex);
			String victimName = victim.getName();
			String builtName = victim.getBuilt().get(builtIndex).getName();
			Card c = victim.getBuilt().remove(builtIndex);
			board.addCoin(c.getBeautifyLevel());
			c.setBeautifyLevel(0);
			board.log(name + "炸毁了" + victimName + "的" + builtName + "。");
			if (c.getCardsUnder().size() > 0) {
				board.log(player.getName() + "将" + victimName + "的" + builtName + "下方的牌一并炸毁了。");
			}
			for (i=c.getCardsUnder().size()-1;i>=0;i--) {
				Card u = c.getCardsUnder().remove(i);
				board.bottomDeck(u);
			}
			board.bottomDeck(c);
			
			Card s = player.getBuilt().remove(index);
			board.addCoin(s.getBeautifyLevel());
			s.setBeautifyLevel(0);
			board.bottomDeck(s);
		}
		return ask;
	}
	
}
