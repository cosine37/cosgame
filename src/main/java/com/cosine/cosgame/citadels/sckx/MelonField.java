package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class MelonField extends Card{
	public MelonField() {
		super();
		name = "瓜田";
		cost = 5;
		img = "p513";
		color = CitadelsConsts.PURPLE;
		trackOtherAssassin = true;
		expansion = 3;
	}
	
	public void onOtherAssassin() {
		player.addCoin(1);
		board.log(player.getName() + "从 瓜田 里的吃瓜群众处获得1￥。");
	}
}
