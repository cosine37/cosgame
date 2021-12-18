package com.cosine.cosgame.threechaodoms.base;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class CaoCao extends Card {
	public CaoCao() {
		name = "曹操";
		courtesy = "孟德";
		img = "CaoCao";
		title = "亂世之奸雄";
		faction = Consts.WEI;
		
		han = -1;
		wei = 1;
		
		desc = "王道-1，霸道+1。";
	}
}
