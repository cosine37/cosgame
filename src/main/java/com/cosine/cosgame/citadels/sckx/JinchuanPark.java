package com.cosine.cosgame.citadels.sckx;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class JinchuanPark extends Card{
	public JinchuanPark() {
		super();
		name = "荆川公园";
		cost = 3;
		img = "p309";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void alterPlayerAbility() {
		player.setProtect(true);
	}
	
	public void protectEffect() {
		player.addCoin(1);
		Card m = player.getBuilt().remove(this.index);
		player.getHand().add(m);
		board.log(player.getName() + "的 荆川公园 保护了" + player.getName() + "不受影响。");
		board.log(player.getName() + "将 荆川公园 收为手牌");
	}
}
