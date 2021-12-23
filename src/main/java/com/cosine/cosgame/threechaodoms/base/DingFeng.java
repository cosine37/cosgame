package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class DingFeng extends Card {
	public DingFeng() {
		name = "丁奉";
		courtesy = "承淵";
		img = "DingFeng";
		title = "雪中奮兵";
		faction = Consts.WU;
		
		desc = "选择一名玩家，该玩家阵面每有一名群势力武将，王道-1，霸道-1（最多-3）。";
		instruction = "请选择一名玩家。";
		
		playType = Consts.CHOOSEPLAYER;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() > 0) {
			int x = targets.get(0);
			Player p = board.getPlayerByIndex(x);
			board.log(player.getName() + "选择了" + p.getName() + "。");
			int t = 0;
			for (int i=0;i<p.getPlay().size();i++) {
				if (p.getPlay().get(i).getFaction() == Consts.QUN) {
					t++;
				}
			}
			if (t>3) t=3;
			board.moveHan(0-t);
			board.moveWei(0-t);
		}
	}
}
