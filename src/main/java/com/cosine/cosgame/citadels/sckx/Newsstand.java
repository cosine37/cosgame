package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Newsstand extends Card{
	public Newsstand() {
		super();
		name = "报刊亭";
		cost = 2;
		img = "p203";
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
		ask = useSkill(index, x);
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 报刊亭 的特效。");
		Card c = cardsUnder.remove(0);
		player.getHand().add(c);
		board.log(name + "将 报刊亭 下的一张牌加入了手牌。");
		if (cardsUnder.size() == 0) {
			board.log(name + "的 报刊亭 感觉身体被掏空，被拆除了。");
			Card n = player.getBuilt().remove(index);
			board.bottomDeck(n);
		}
		return ask;
	}
	
	public void onBuild() {
		Card c = board.getDeck().remove(0);
		cardsUnder.add(c);
		c = board.getDeck().remove(0);
		cardsUnder.add(c);
		board.log(player.getName() + "将牌堆顶的2张牌放置于 报刊亭 下。");
	}
}
