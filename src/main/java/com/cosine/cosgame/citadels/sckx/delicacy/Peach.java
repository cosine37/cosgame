package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class Peach extends Delicacy{
	public Peach() {
		name = "阳湖水蜜桃";
		img = "d001";
		cost = 0;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getCoin() == 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() == 0) {
				board.log(p.getName() + "白嫖了 阳湖水蜜桃。");
				p.addCoin(1);
				p.getCanBuyDelicacy().set(index, "n");
				board.log(p.getName() + "获得了1￥。");
			} else {
				board.log(p.getName() + "然而" +p.getName() +"依然有￥，所以无事发生。");
			}
			
		}
		
		return ask;
	}
}
