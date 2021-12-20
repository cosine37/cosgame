package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class SiMaYi extends Card {
	public SiMaYi() {
		name = "司馬懿";
		courtesy = "仲達";
		img = "SiMaYi";
		title = "冢虎";
		faction = Consts.WEI;
		
		han = 1;
		wei = 1;
		
		desc = "王道+1，霸道+1。将你的一张手牌加入你的监狱.";
		instruction = "请选择一张手牌，该手牌将会加入你的监狱。";
		
		playType = Consts.CHOOSEHAND;
		playSubType = Consts.INJAIL;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			player.putInJail(x);
		}
	}
}
