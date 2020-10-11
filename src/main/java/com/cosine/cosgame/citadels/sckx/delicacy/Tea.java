package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class Tea extends Delicacy{
	public Tea() {
		name = "天目湖白茶";
		img = "d102";
		cost = 1;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getCoin() >= cost) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		board.log(p.getName() + "花费了1￥购买了 天目湖白茶。");
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= cost) {
				p.getCanBuyDelicacy().set(index, "n");
				int x = p.getCoin() - cost;
				p.setCoin(x);
				board.addCoin(cost);
				p.draw(1);
				board.log(p.getName() + "摸了一张牌。");
			}
		}
		
		return ask;
	}
}
