package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class SesameCake extends Delicacy{
	public SesameCake() {
		name = "大麻糕";
		img = "d101";
		cost = 1;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getCoin() > 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		board.log(p.getName() + "花费了1￥购买了 大麻糕。");
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= cost) {
				p.getCanBuyDelicacy().set(index, "n");
				int x = p.getCoin() - cost;
				p.setCoin(x);
				board.addCoin(cost);
				x = p.getNumBuilt()-1;
				p.setNumBuilt(x);
				board.log(p.getName() + "本回合可以额外建造一个建筑。");
			}
		}
		
		return ask;
	}
}
