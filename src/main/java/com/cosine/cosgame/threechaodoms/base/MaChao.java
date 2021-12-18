package com.cosine.cosgame.threechaodoms.base;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class MaChao extends Card {
	public MaChao() {
		name = "馬超";
		courtesy = "孟起";
		img = "MaChao";
		title = "神威天將軍";
		faction = Consts.HAN;
		
		han = 3;
		wei = 1;
		
		desc = "王道+3，霸道+1。";
	}
}
