package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class ForebackNorthBay extends Card{
	public ForebackNorthBay() {
		super();
		name = "前后北岸";
		cost = 3;
		img = "p308";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		player.addCoin(1);
		player.draw(2);
		List<String> ls = new ArrayList<>();
		for (int i=0;i<player.getHand().size();i++) {
			ls.add("y");
		}
		Ask ask = new Ask();
		ask.setAskId(99308);
		ask.setAskType(CitadelsConsts.CHOOSEONECARDFROMHAND);
		ask.setAskBuiltIndex(index);
		ask.setMsg("请将一张手牌置于牌堆顶。");
		ask.setLs(ls);
		player.setAsk(ask);
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 前后北岸 的特效。");
		if (player.getHand().size() > x) {
			Card c = player.getHand().remove(x);
			board.topDeck(c);
			board.log(name + "将1张手牌置于牌堆顶 。");
		}
		return ask;
	}
}
