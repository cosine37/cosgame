package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class MacawTown extends Card{
	public MacawTown() {
		super();
		name = "澳门城";
		cost = 3;
		img = "p303";
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
		board.log(name + "发动了建筑 澳门城 的特效。");
		if (player.getCoin() >= 3) {
			Card revealedCard = board.getDeck().remove(0);
			List<String> tempRevealedTop = new ArrayList<>();
			tempRevealedTop.add(revealedCard.getImg());
			board.setTempRevealedTop(tempRevealedTop);
			board.log(name + "花费了3￥展示了 " + revealedCard.getName() + " 。");
			int c = player.getCoin()-3+revealedCard.getCost();
			player.setCoin(c);
			board.log(name + "获得了" + Integer.toString(revealedCard.getCost()) + "￥。");
			board.bottomDeck(revealedCard);
			board.log(name + "将 " + revealedCard.getName() + " 置于牌堆底。");
		} else {
			board.log("然而，穷鬼" + name + "拿不出3￥，所以无事发生。");
		}
		return ask;
	}
}
