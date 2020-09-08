package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class QingfengPark extends Card{
	public QingfengPark() {
		super();
		name = "青枫公园";
		cost = 5;
		img = "p510";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onSelectChooseCard() {
		super.onSelectChooseCard();
		if (beautifyLevel < 3) {
			int x = board.takeCoins(1);
			if (x>0) {
				beautify();
				board.log(player.getName() + "的 青枫公园 价值上升。");
			}
		}
	}
}
