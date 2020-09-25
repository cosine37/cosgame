package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class HuPalace extends Card{
	public HuPalace() {
		super();
		name = "护王府";
		cost = 4;
		img = "p408";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void afterTakeActionEffect() {
		String name = player.getName();
		if (player.getRole().getNum() == 1 || player.getRole().getNum() == 2) {
			if (this.beautifyLevel < 3) {
				int x = board.takeCoins(1);
				if (x>0) {
					beautify();
				}
				board.log("因为" + name + "选择了" + player.getRole().getName() + "，护王府升值了。");
			}
			
		}
		
	}
}
