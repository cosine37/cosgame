package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;
import com.cosine.cosgame.citadels.Player;

public class Fort extends Card{
	public Fort() {
		super();
		name = "古炮台";
		cost = 6;
		img = "p615";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public String canUseSkill() {
		return "y";
	}
	
	public String canUseSkillSameRound() {
		return "y";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		if (cardsUnder.size() == 0) {
			if (player.getHand().size() > 1) {
				List<String> ls = new ArrayList<>();
				for (int i=0;i<player.getHand().size();i++) {
					ls.add("y");
				}
				ask.setAskId(98615);
				ask.setAskType(5);
				ask.setAskBuiltIndex(index);
				ask.setMsg("请将一张手牌置于 炮台 内作为弹药。");
				ask.setLs(ls);
			} else {
				ask = useSkill(index, x);
			}
		} else {
			boolean flag = true;
			int i,j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				if (player.getName().contentEquals(p.getName())) {
					continue;
				}
				int builtSize = 0;
				for (j=0;j<p.getBuilt().size();j++) {
					builtSize = builtSize + p.getBuilt().get(j).buildCount();
				}
				if (builtSize >= board.getFinishCount()) {
					continue;
				}
				for (j=0;j<p.getBuilt().size();j++) {
					int z = p.getBuilt().get(j).getCost() + p.getBuilt().get(j).getBeautifyLevel();
					if (z<=4) {
						flag = false;
						break;
					}
				}
				
			}
			if (flag) {
				ask = useSkill(index, x);
			} else {
				ask.setAskId(99615);
				ask.setAskType(CitadelsConsts.CHOOSEBUILT);
				ask.setMsg("请选择一个你要轰击的建筑。");
				List<List<String>> builtInfo = new ArrayList<>();
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
							int z = p.getBuilt().get(j).getCost() + p.getBuilt().get(j).getBeautifyLevel();
							if (z>4) {
								singleBuiltInfo.add("-1");
							} else {
								singleBuiltInfo.add("0");
							}
						}
					}
					builtInfo.add(singleBuiltInfo);
				}
				ask.setBuiltInfo(builtInfo);
			}
		}
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		System.out.println("Cards Under Size:" + cardsUnder.size());
		if (cardsUnder.size() == 0) {
			if (player.getHand().size() == 0) {
				board.log(name + "发动了建筑 古炮台 的特效。");
				board.log("然而" + name + "没有手牌，所以无事发生。");
			} else {
				if (player.getHand().size() == 1) {
					x = 0;
				}
				Card c = player.getHand().remove(x);
				Card f = player.getBuilt().get(index);
				f.getCardsUnder().add(c);
				board.log(name + "给 古炮台 装上了弹药。");
			}
		} else {
			boolean flag = true;
			int i,j;
			for (i=0;i<board.getPlayers().size();i++) {
				Player p = board.getPlayers().get(i);
				if (player.getName().contentEquals(p.getName())) {
					continue;
				}
				int builtSize = 0;
				for (j=0;j<p.getBuilt().size();j++) {
					builtSize = builtSize + p.getBuilt().get(j).buildCount();
				}
				if (builtSize >= board.getFinishCount()) {
					continue;
				}
				for (j=0;j<p.getBuilt().size();j++) {
					int z = p.getBuilt().get(j).getCost() + p.getBuilt().get(j).getBeautifyLevel();
					if (z<=4) {
						flag = false;
						break;
					}
				}
			}
			if (flag) {
				board.log(name + "发动了建筑 古炮台 的特效。");
				board.log("然而没有可以轰击的建筑，所以无事发生。");
			} else {
				int temp = x%10000;
				int playerIndex = temp/100;
				int builtIndex = temp%100;
				Player victim = board.getPlayers().get(playerIndex);
				String victimName = victim.getName();
				String builtName = victim.getBuilt().get(builtIndex).getName();
				Card c = victim.getBuilt().remove(builtIndex);
				board.addCoin(c.getBeautifyLevel());
				c.setBeautifyLevel(0);
				board.log(name + "使用了 古炮台 轰击并炸毁了" + victimName + "的" + builtName + "。");
				if (c.getCardsUnder().size() > 0) {
					board.log(player.getName() + "将" + victimName + "的" + builtName + "下方的牌一并炸毁了。");
				}
				for (i=c.getCardsUnder().size()-1;i>=0;i--) {
					Card u = c.getCardsUnder().remove(i);
					board.bottomDeck(u);
				}
				board.bottomDeck(c);
				
				Card s = player.getBuilt().get(index).getCardsUnder().remove(0);
				board.bottomDeck(s);
			}
		}
		return ask;
	}
	
}
