package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class DongpoPark extends Card{
	public DongpoPark() {
		super();
		name = "东坡公园";
		cost = 5;
		img = "p514";
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
		board.log(name + "发动了建筑 东坡公园 的特效。");
		
		String info = name + "展示了 ";
		String secondInfo;
		List<String> tempRevealedTop = new ArrayList<>();
		int cardReceived = 0;
		Card revealedCard = board.getDeck().remove(0);
		info = info + revealedCard.getName() + " 和 ";
		tempRevealedTop.add(revealedCard.getImg());
		if (revealedCard.getColor() == CitadelsConsts.PURPLE || revealedCard.getColor() == CitadelsConsts.GREENPURPLE) {
			player.getHand().add(revealedCard);
			secondInfo = name + "将 " + revealedCard.getName() + " 收为手牌。";
		} else {
			board.bottomDeck(revealedCard);
			secondInfo = name + "将 " + revealedCard.getName() + " 置于牌堆底。";
		}
		revealedCard = board.getDeck().remove(0);
		info = info + revealedCard.getName() + "。";
		board.log(info);
		board.log(secondInfo);
		tempRevealedTop.add(revealedCard.getImg());
		board.setTempRevealedTop(tempRevealedTop);
		if (revealedCard.getColor() == CitadelsConsts.PURPLE || revealedCard.getColor() == CitadelsConsts.GREENPURPLE) {
			player.getHand().add(revealedCard);
			board.log(name + "将 " + revealedCard.getName() + " 收为手牌。");
		} else {
			board.bottomDeck(revealedCard);
			board.log(name + "将 " + revealedCard.getName() + " 置于牌堆底。");
		}
		
		
		return ask;
	}
}
