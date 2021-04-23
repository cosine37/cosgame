package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Canis;

public class AlphaWolf extends Role{
	public AlphaWolf() {
		super();
		roleNum = 36;
		side = Consts.WOLF;
		img = "r36";
		sequence = -600;
		name = "狼卓";
		chooseStatusNum = 1;
		useStatus = true;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 狼卓。");
		duskMsg.add("你可以给一名其他玩家施加【狼种】状态，拥有该状态的玩家阵营为“狼”。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 狼卓。");
		nightMsg.add("状态显示为【狼种】的玩家是你指定的目标，拥有该状态的玩家阵营为“狼”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼卓。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼卓，你现在的身份可能已有变化。");
		dayMsg.add("状态显示为【狼种】的玩家是你指定的目标，其当前状态不一定是【狼种】，拥有该状态的玩家阵营为“狼”。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼卓，你现在的身份可能已有变化。");
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
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.CANIS);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.CANIS) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Canis());
			}
		}
	}
}
