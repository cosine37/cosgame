package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class ShootMoonBay extends Card{
	public ShootMoonBay() {
		super();
		name = "揽月湾";
		cost = 5;
		img = "p515";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		if (player.getCoin() > 0) {
			boolean flag = false;
			List<String> ls = new ArrayList<>();
			for (int i=0;i<player.getHand().size();i++) {
				Card c = player.getHand().get(i);
				if (player.canBuildFreeNoLimit(i) && c.getCost() <= 3) {
					flag = true;
					ls.add("y");
				} else {
					ls.add("n");
				}
			}
			if (flag) {
				Ask ask = new Ask();
				ask.setAskId(99515);
				ask.setAskType(CitadelsConsts.CHOOSEONECARDFROMHAND);
				ask.setAskBuiltIndex(index);
				ask.setMsg("你可以花费1￥额外建造一张价格不超过3￥的手牌（不需支付其价格）。");
				ask.setLs(ls);
				player.setAsk(ask);
			}
		}
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = super.useSkill(index, x);
		String name = player.getName();
		board.log(name + "发动了建筑 揽月湾 的特效。");
		if (player.getHand().size() > x && player.getCoin() > 0) {
			int y = player.getCoin()-1;
			player.setCoin(y);
			Card c = player.getHand().get(x);
			board.log(name + "花费了1￥额外建造了  " + c.getName() + " 。");
			player.freeBuild(x);
			
		}
		return ask;
	}
}
