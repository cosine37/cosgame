package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class XuHuang extends Card {
	public XuHuang() {
		name = "徐晃";
		courtesy = "公明";
		img = "XuHuang";
		title = "長驅直入";
		faction = Consts.WEI;
		
		desc = "选择一名玩家，该玩家阵面每有一名蜀势力武将，霸道+1（最多+3）。";
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
				if (p.getPlay().get(i).isFaction(Consts.HAN)) {
					t++;
				}
			}
			if (t>3) t=3;
			board.moveWei(t);
		}
	}
}
