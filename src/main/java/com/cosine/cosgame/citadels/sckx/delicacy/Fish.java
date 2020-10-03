package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class Fish extends Delicacy{
	public Fish() {
		name = "砂锅鱼头";
		img = "d501";
		cost = 0;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getCoin() >= 5) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= 5) {
				board.log(p.getName() + "花费了5￥购买了 砂锅鱼头。");
				p.addCoin(1);
				p.getCanBuyDelicacy().set(index, "n");
				board.log(p.getName() + "获得了6￥。");
			}
		}
		
		return ask;
	}
}
