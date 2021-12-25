package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class YuanShu extends Card {
	public YuanShu() {
		name = "袁術";
		courtesy = "公路";
		img = "YuanShu";
		title = "冢中枯骨";
		faction = Consts.QUN;
		
		han = -1;
		wei = -1;
		
		desc = "王道+1，霸道+2。你可以將墓地顶的武将和你阵面的武将交换。";
		
		playType = Consts.CHOOSEPLAYOPTION;
		playSubType = Consts.MYCARD;
	}
	
	public List<String> getOptionsDisplay() {
		options = new ArrayList<>();
		if (board.getTomb().size() == 0) {
			options.add("墓地没武将，无事发生");
		} else {
			options.add("不交换");
			options.add("交换");
		}
		return options;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			int o = targets.get(0);
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y && o == 1) {
				Card c1 = board.getTomb().remove(0);
				Card c2 = p.getPlay().remove(y);
				board.addToTomb(c2);
				player.getPlay().add(c1);
				board.log("墓地顶的" + c1.getName() + "加入" + p.getName() + "的阵面。", c1, "置于阵面");
				board.log(p.getName() + "阵面的" + c2.getName() + "被击杀了。", c2, "袁术击杀");
				
			}
		}
		
	}
}
