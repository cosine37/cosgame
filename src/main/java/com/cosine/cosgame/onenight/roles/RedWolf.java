package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class RedWolf extends Role{
	public RedWolf() {
		super();
		roleNum = 71;
		side = Consts.WOLF;
		img = "r71";
		sequence = 200;
		name = "红太狼";
		hasNight = true;
		nightMsg.add("你的初始身份是 红太狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 红太狼。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 红太狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("若你依然是红太狼，投票阶段若有一名玩家的身份为灰太狼，你的胜利条件改为你不出局且灰太狼被投票出局。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 红太狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("若你依然是红太狼，投票阶段若有一名玩家的身份为灰太狼，你的胜利条件改为你不出局且灰太狼被投票出局。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public boolean win(boolean f) {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			Role r = p.getCurrentRole();
			if (r.getRoleNum() == Consts.GREYWOLF) {
				if (p.isVotedOut() && (player.isVotedOut() == false)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return f;
	}
}
