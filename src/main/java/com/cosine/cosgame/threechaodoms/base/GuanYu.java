package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class GuanYu extends Card {
	public GuanYu() {
		name = "關羽";
		courtesy = "雲長";
		img = "GuanYu";
		title = "威震華夏";
		faction = Consts.HAN;
		
		desc = "选择一名玩家，该玩家阵面每有一名魏势力武将，王道+1（最多+3）。";
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
				if (p.getPlay().get(i).isFaction(Consts.WEI)) {
					t++;
				}
			}
			if (t>3) t=3;
			board.moveHan(t);
		}
	}
}
