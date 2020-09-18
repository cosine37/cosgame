package com.cosine.cosgame.citadels.sckx.omnicolor;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Agency extends Card{
	public Agency() {
		super();
		name = "办事处";
		cost = 2;
		img = "o21p";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		boolean flag = false;
		List<String> ls = new ArrayList<>();
		for (int i=0;i<player.getHand().size();i++) {
			Card c = player.getHand().get(i);
			if (c.getColor() < 10 && c.getColor()!=CitadelsConsts.PURPLE) {
				flag = true;
				ls.add("y");
			} else {
				ls.add("n");
			}
		}
		if (flag) {
			Ask ask = new Ask();
			ask.setAskId(99921);
			ask.setAskType(CitadelsConsts.CHOOSEONECARDFROMHAND);
			ask.setAskBuiltIndex(index);
			ask.setMsg("你可以弃置一张手牌并将办事处的类型改为弃置牌的类型。");
			ask.setLs(ls);
			player.setAsk(ask);
		}
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		if (player.getHand().size() > x) {
			board.log(name + "发动了建筑 办事处 的特效。");
			Card c = player.getHand().remove(x);
			if (c.getColor() == CitadelsConsts.GREEN) {
				color = c.getColor();
				img = "o21g";
				board.log(name + "的 办事处 变成了 商业类型。");
			} else if (c.getColor() == CitadelsConsts.BLUE) {
				color = c.getColor();
				img = "o21b";
				board.log(name + "的 办事处 变成了 教育类型。");
			} if (c.getColor() == CitadelsConsts.RED) {
				color = c.getColor();
				img = "o21r";
				board.log(name + "的 办事处 变成了 治安类型。");
			} if (c.getColor() == CitadelsConsts.YELLOW) {
				color = c.getColor();
				img = "o21y";
				board.log(name + "的 办事处 变成了 市政类型。");
			} 
			board.bottomDeck(c);
		}
		return ask;
	}
	
}
