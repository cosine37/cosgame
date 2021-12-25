package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;
import com.cosine.cosgame.threechaodoms.Player;

public class YanLiang extends Card {
	public YanLiang() {
		name = "颜良";
		courtesy = "";
		img = "YanLiang";
		title = "勇冠三軍";
		faction = Consts.QUN;
		
		desc = "领先标记-2。击杀另一名玩家阵面或监狱中的一名武将。";
		
		playType = Consts.CHOOSEPLAYJAIL;
		playSubType = Consts.OTHERPLAYER;
	}
	
	public String getDescDisplay() {
		String ans = desc;
		if (board.getHanPos() > board.getWeiPos()) {
			ans = ans + " (王道-2)";
		} else if (board.getHanPos() < board.getWeiPos()) {
			ans = ans + " (霸道-2)";
		} else {
			ans = ans + " (无领先标记)";
		}
		return ans;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (targets.size()>3) {
			if (board.getHanPos() > board.getWeiPos()) {
				board.moveHan(-2);
			} else if (board.getHanPos() < board.getWeiPos()) {
				board.moveWei(-2);
			} else {
			}
			int x = targets.get(1);
			int y1 = targets.get(2);
			int y2 = targets.get(3);
			if (y1 != -1) {
				int y = y1;
				Player p = board.getPlayerByIndex(x);
				if (p != null && p.getPlay().size() > y) {
					Card c = p.getPlay().remove(y);
					board.addToTomb(c);
					board.log(p.getName() + "阵面的" + c.getName() + "被击杀了。", c, "颜良击杀");
				}
			} else if (y2!=-1) {
				int y = y2;
				Player p = board.getPlayerByIndex(x);
				if (p != null && p.getJail().size() > y) {
					Card c = p.getJail().remove(y);
					board.addToTomb(c);
					board.log(p.getName() + "监狱中的" + c.getName() + "被击杀了。", c, "颜良击杀");
				}
			}
			
		}
		
	}
}
