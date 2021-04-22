package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Confused;

public class Instigator extends Role{
	public Instigator() {
		super();
		roleNum = 59;
		side = Consts.HUMAN;
		img = "r59";
		sequence = -300;
		name = "煽动者";
		chooseStatusNum = 1;
		canChooseSelfStatus = true;
		useStatus = true;
		hasDusk = true;
		duskMsg.add("你的身份是 煽动者。");
		duskMsg.add("你可以给一名玩家施加【煽惑】状态。");
		duskMsg.add("拥有【煽惑】状态的玩家胜利条件变为自己的队友被投票出局，如无队友则该状态对其无效。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 煽动者。");
		nightMsg.add("显示【煽惑】状态的玩家是你使用技能的目标。");
		nightMsg.add("拥有【煽惑】状态的玩家胜利条件变为自己的队友被投票出局，如无队友则该状态对其无效。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 煽动者。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 煽动者，你现在的身份可能已有变化。");
		dayMsg.add("显示【煽惑】状态的玩家是你使用技能的目标，其当前状态不一定为【煽惑】。");
		dayMsg.add("拥有【煽惑】状态的玩家胜利条件变为自己的队友被投票出局，如无队友则该状态对其无效。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 煽动者，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.CONFUSED);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.CONFUSED) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Confused());
			}
		}
	}
}
