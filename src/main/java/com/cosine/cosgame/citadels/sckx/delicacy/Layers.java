package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class Layers extends Delicacy{
	public Layers() {
		name = "横山桥百叶";
		img = "d201";
		cost = 2;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getHand().size() > 0) {
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
		board.log(p.getName() + "花费了2￥购买了 横山桥百叶。");
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= cost && p.getHand().size() == 0) {
				int x = p.getCoin() - cost;
				p.setCoin(x);
				board.addCoin(cost);
				p.draw(3);
				board.log(p.getName() + "获得3张牌。");
			}
			
		}
		
		return ask;
	}
}
