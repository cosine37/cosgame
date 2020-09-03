package com.cosine.cosgame.citadels.sckx;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class CommodityMarket extends Card{
	public CommodityMarket() {
		super();
		name = "小商品市场";
		cost = 4;
		img = "p404";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void onBuild() {
		player.addCoin(1);
		player.draw(1);
		board.log("因为 小商品市场 的效果，" + player.getName() + "获得了1￥并摸了1张牌。");
	}
}
