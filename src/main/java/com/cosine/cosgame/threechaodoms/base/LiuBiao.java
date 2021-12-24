package com.cosine.cosgame.threechaodoms.base;

import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class LiuBiao extends Card {
	public LiuBiao() {
		name = "劉表";
		courtesy = "景升";
		img = "LiuBiao";
		title = "雄踞荆州";
		faction = Consts.QUN;
		
		desc = "王道+2，霸道+1。击杀任意一名玩家阵面的一名吴势力武将。";
		
		playType = Consts.CHOOSEPLAY;
		playSubType = Consts.CHOOSEPLAYWU;
		
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size() >2) {
			board.moveHan(2);
			board.moveWei(1);
			int x = targets.get(1);
			int y = targets.get(2);
			Player p = board.getPlayerByIndex(x);
			if (player != null && p.getPlay().size() > y) {
				Card c = p.getPlay().remove(y);
				board.addToTomb(c);
				board.log(p.getName() + "阵面的" + c.getName() + "被击杀了。", c, "刘表击杀");
			}
		}
		
	}
}
