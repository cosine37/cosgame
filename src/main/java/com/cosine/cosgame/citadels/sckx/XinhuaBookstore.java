package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class XinhuaBookstore extends Card{
	public XinhuaBookstore() {
		super();
		name = "新华书店";
		cost = 3;
		img = "p307";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public String canUseSkill() {
		if (cardsUnder.size() == 0) {
			return "n";
		} else {
			return "y";
		}
		
	}
	
	public String canUseSkillSameRound() {
		if (cardsUnder.size() == 0) {
			return "n";
		} else {
			return "y";
		}
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = super.chooseSkill(index, x);
		ask = useSkill(index, x);
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		String name = player.getName();
		board.log(name + "发动了建筑 新华书店 的特效。");
		Card c = cardsUnder.remove(0);
		player.getHand().add(c);
		board.log(name + "将 新华书店 下的一张牌加入了手牌。");
		Ask ask;
		if (cardsUnder.size() == 0) {
			ask = super.useSkill(index, x);
		} else {
			ask = new Ask();
		}
		return ask;
	}
	
	public void onBuild() {
		int x = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			Card b = player.getBuilt().get(i);
			if (b.getColor() == CitadelsConsts.BLUE
					|| b.getColor() == CitadelsConsts.GREENBLUE
					|| b.getColor() == CitadelsConsts.BLUERED
					|| b.getColor() == CitadelsConsts.BLUEYELLOW) {
				Card c = board.getDeck().remove(0);
				cardsUnder.add(c);
				x++;
			}
		}
		board.log(player.getName() + "将牌堆顶的" + Integer.toString(x) + "张牌放置于 新华书店 下。");
	}
}
