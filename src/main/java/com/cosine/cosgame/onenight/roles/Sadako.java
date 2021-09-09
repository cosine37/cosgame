package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Sadako extends Role{
	public Sadako() {
		super();
		roleNum = 69;
		side = Consts.SADAKO;
		img = "r69";
		sequence = 200;
		name = "山村贞子";
		hasNight = true;
		nightMsg.add("你的初始身份是 山村贞子。");
		nightMsg.add("场上显示身份牌的玩家初始身份为“狼”阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 山村贞子。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 山村贞子，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是山村贞子且出局，你单独获得胜利。");
		votedMsg.add("你的初始身份是 山村贞子，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是山村贞子且出局，你单独获得胜利。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF && p.getInitialRole().getRoleNum() != Consts.MINION) {
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNumToShow());
			}
		}
		Manipulations.soleWolfHandle(player, board);
	}
	
	public boolean win(boolean f) {
		if (player.isVotedOut()) {
			return true;
		}
		return false;
	}
	
	public int getSide() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			return side;
		} else {
			return Consts.WOLF;
		}
	}
}
