package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class GuoSi extends Card {
	public GuoSi() {
		name = "郭汜";
		courtesy = "";
		img = "GuoSi";
		title = "盜馬虜";
		faction = Consts.QUN;
		
		han = 1;
		
		desc = "王道+1，霸道+1，选择一名玩家，随机击杀其一名阵面武将。";
		instruction = "请选择一名玩家。";
		
		playType = Consts.CHOOSEPLAYER;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			Player p = board.getPlayerByIndex(x);
			if (p.getPlay().size() > 0) {
				Random rand = new Random();
				int t = rand.nextInt(p.getPlay().size());
				Card c = p.getPlay().remove(t);
				board.addToTomb(c);
			}
			
		}
	}
}
