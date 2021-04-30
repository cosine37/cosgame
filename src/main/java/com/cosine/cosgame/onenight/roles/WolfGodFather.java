package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class WolfGodFather extends Role{
	public WolfGodFather() {
		super();
		roleNum = 50;
		side = Consts.WOLF;
		img = "r50";
		sequence = 200;
		name = "狼教父";
		hasNight = true;
		nightMsg.add("你的初始身份是 狼教父。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼教父。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼教父，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你投的玩家为“狼”阵营，该玩家的票数将清零。");
		votedMsg.add("你的初始身份是 狼教父，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
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
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNum());
			}
		}
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void afterVoteCountHandle() {
		int x = player.getVoteIndex();
		if (x>=0 && x<board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			if (p.getCurrentRole().getSide() == Consts.WOLF) {
				p.setNumVotes(0);
			}
		}
	}
}
