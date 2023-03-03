package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Stoned;

public class Lycanroc extends Role{
	public Lycanroc() {
		super();
		roleNum = 58;
		side = Consts.WOLF;
		img = "r58";
		sequence = -550;
		name = "鬃岩狼人";
		chooseStatusNum = 1;
		useStatus = true;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 鬃岩狼人。");
		duskMsg.add("你可以给一名其他玩家施加【石化】状态，夜晚阶段开始时拥有该状态的玩家至天亮前使用技能失效。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 鬃岩狼人。");
		nightMsg.add("状态显示为【石化】的玩家是你指定的目标。该玩家夜晚阶段使用的技能将会失效。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 鬃岩狼人。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 鬃岩狼人，你现在的身份可能已有变化。");
		dayMsg.add("状态显示为【石化】的玩家是你指定的目标，其当前状态不一定是【石化】，该玩家夜晚阶段使用的技能失效。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 鬃岩狼人，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.STONED);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.STONED) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Stoned());
			}
		}
	}
}
