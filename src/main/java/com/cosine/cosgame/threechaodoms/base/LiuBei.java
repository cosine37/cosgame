package com.cosine.cosgame.threechaodoms.base;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class LiuBei extends Card {
	public LiuBei() {
		name = "劉備";
		courtesy = "玄德";
		img = "LiuBei";
		title = "雄才蓋世";
		faction = Consts.HAN;
		
		han = 1;
		wei = -1;
		
		desc = "王道+1，霸道-1。";
	}
}
