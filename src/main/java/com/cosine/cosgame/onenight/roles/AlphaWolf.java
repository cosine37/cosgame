package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class AlphaWolf extends Role{
	public AlphaWolf() {
		super();
		roleNum = 36;
		side = Consts.WOLF;
		img = "r36";
		sequence = 590;
		name = "狼卓";
		choosePlayerNum = 1;
		canChooseBoth = true;
		hasNight = true;
		nightMsg.add("你的初始身份是 狼卓。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("你可以将一名其他玩家身份变为“狼人”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼卓。");
		confirmedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你指定的变为“狼人”的玩家。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼卓，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你指定的变为“狼人”的玩家，但当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼卓，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你指定的变为“狼人”的玩家，但当前身份可能已有变化。");
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
	private void makeChange(int t1) {
		if (board.getPlayers().get(t1).getCurrentRole().exchangable()) {
			Role r = new QuoteWerewolf2();
			board.getPlayers().get(t1).addRole(r);
			int x = board.getPlayers().get(t1).getCurrentRole().getRoleNum();
			player.getPlayerMarks().set(t1, x);
		}
	}
	
	public void useSkill(int t1) {
		if (t1>=0 && t1 < board.getPlayers().size()) {
			makeChange(t1);
		}
	}
	
}
