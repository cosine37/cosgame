package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Canis;

public class WolfCook extends Role{
	public WolfCook() {
		super();
		roleNum = 80;
		side = Consts.WOLF;
		img = "r80";
		sequence = -800;
		name = "炊事狼";
		choosePlayerNum = 1;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 炊事狼。");
		duskMsg.add("指定一名其他玩家，若该玩家的初始身份为“人”阵营，该玩家中毒。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 炊事狼。");
		nightMsg.add("身份牌上有标记的玩家是你的目标，若该玩家的初始身份为“人”阵营，该玩家中毒，但该玩家可能被解毒。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 炊事狼。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 炊事狼，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上有标记的玩家是你的目标，若该玩家的初始身份为“人”阵营，该玩家中毒，但该玩家可能被解毒。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 炊事狼，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.POISONED);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.POISONED) {
				board.getPlayers().get(i).poison();
			}
		}
	}
}
