package com.cosine.cosgame.citadels.sckx.delicacy;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class Wine extends Delicacy{
	public Wine() {
		name = "金坛封缸酒";
		img = "d002";
		cost = 0;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.hasCrown()) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.hasCrown()) {
				board.log(p.getName() + "白嫖了 金坛封缸酒。");
				p.addCoin(1);
				p.getCanBuyDelicacy().set(index, "n");
				board.log(p.getName() + "获得了1￥。");
			} else {
				board.log(p.getName() + "然而" +p.getName() +"并没有市长标记，所以无事发生。");
			}
			
		}
		
		return ask;
	}
}
