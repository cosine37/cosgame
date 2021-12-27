package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class CaiWenJi extends Card {
	public CaiWenJi() {
		name = "蔡琰";
		courtesy = "文姬";
		img = "CaiWenJi";
		title = "胡笳悲歌";
		faction = Consts.QUN;
		
		han = 1;
		wei = 1;
		
		desc = "王道+1，霸道+1。将另一名玩家阵面的一名武将和酒馆的一名武将交换。";
		
		playType = Consts.CHOOSEPLAYOPTION;
		playSubType = Consts.OTHERPLAYER;

	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		for (int i=0;i<board.getTavern().size();i++) {
			Card c = board.getTavern().get(i);
			options.add("与" + c.getName() + "交换");
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >4) {
			int option1 = targets.get(0);
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y && option1>=0 && option1<3) {
				Card c1 = p.getPlay().get(y);
				Card c2 = board.getTavern().get(option1);
				board.getTavern().set(option1, c1);
				p.getPlay().set(y, c2);
				board.log(p.getName() + "阵面的" + c1.getName() + "与酒馆中的" + c2.getName() + "交换了。");
			}
		}
		
	}
}
