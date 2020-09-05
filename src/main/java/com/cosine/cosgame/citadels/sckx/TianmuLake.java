package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class TianmuLake extends Card{
	public TianmuLake() {
		super();
		name = "天目湖";
		cost = 5;
		img = "p509";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void afterTakeActionEffect() {
		Card revealedCard = board.getDeck().remove(0);
		String name = player.getName();
		board.log(name + "发动了建筑 天目湖 的特效。");
		List<String> tempRevealedTop = new ArrayList<>();
		tempRevealedTop.add(revealedCard.getImg());
		board.setTempRevealedTop(tempRevealedTop);
		board.log(name + "展示了 " + revealedCard.getName() + " 。");
		int color = revealedCard.getColor();
		int count = 0;
		for (int i=0;i<player.getBuilt().size();i++) {
			if (color == player.getBuilt().get(i).getColor()) {
				count++;
			}
		}
		player.addCoin(count);
		board.log(name + "共有 " + Integer.toString(count) + "个相同类型建筑，获得了" + Integer.toString(count) + "￥。");
		board.bottomDeck(revealedCard);
		board.log(name + "将 " + revealedCard.getName() + " 置于牌堆底。");
	}
}
