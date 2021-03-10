package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Minion extends Role{
	public Minion() {
		super();
		roleNum = 5;
		side = Consts.WOLF;
		img = "r05";
		sequence = 300;
		name = "帮凶";
		nightMsg.add("你的初始身份是 帮凶。");
		nightMsg.add("身份牌上有标记的玩家初始身份和你同一阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 帮凶。");
		confirmedMsg.add("身份牌上有标记的玩家初始身份和你同一阵营。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 帮凶，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上有标记的玩家初始身份和你同一阵营，且当前阵营可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 帮凶，你现在的身份可能已有变化。");
		votedMsg.add("身份牌上有标记的玩家初始身份和你同一阵营，且当前阵营可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF) {
				player.getPlayerMarks().set(i, Consts.WOLFMARK);
			}
		}
	}
}
