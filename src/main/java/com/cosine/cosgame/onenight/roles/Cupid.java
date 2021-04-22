package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Love;

public class Cupid extends Role{
	public Cupid() {
		super();
		roleNum = 56;
		side = Consts.HUMAN;
		img = "r56";
		sequence = -400;
		name = "丘比特";
		chooseStatusNum = 2;
		canChooseSelfStatus = true;
		useStatus = true;
		hasDusk = true;
		duskMsg.add("你的身份是 丘比特。");
		duskMsg.add("你可以给最多两名玩家施加【爱心】状态。所有拥有【爱心】状态的玩家互相认识。");
		duskMsg.add("投票阶段若拥有【爱心】状态的玩家被投票出局，则另一个拥有【爱心】状态的玩家也出局。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 丘比特。");
		nightMsg.add("显示【爱心】状态的玩家是你使用技能的目标。所有拥有【爱心】状态的玩家互相认识。");
		nightMsg.add("投票阶段若拥有【爱心】状态的玩家被投票出局，则另一个拥有【爱心】状态的玩家也出局。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 丘比特。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 丘比特，你现在的身份可能已有变化。");
		dayMsg.add("显示【爱心】状态的玩家是你使用技能的目标。所有拥有【爱心】状态的玩家互相认识。");
		dayMsg.add("投票阶段若拥有【爱心】状态的玩家被投票出局，则另一个拥有【爱心】状态的玩家也出局。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 丘比特，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.LOVE);
		}
	}
	
	public void useSkill(int t1, int t2) {
		t1 = t1-200;
		t2 = t2-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.LOVE);
		}
		if (t2 < board.getPlayers().size()) {
			player.getStatusMarks().set(t2, Consts.LOVE);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.LOVE) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Love());
			}
		}
	}
}
