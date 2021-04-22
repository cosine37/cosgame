package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Assassination;

public class Assassin extends Role{
	public Assassin() {
		super();
		roleNum = 61;
		side = Consts.OTHER;
		img = "r61";
		sequence = -150;
		name = "刺客";
		chooseStatusNum = 1;
		canChooseSelfStatus = true;
		useStatus = true;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 刺客。");
		duskMsg.add("你可以给一名玩家施加【暗杀】状态。");
		duskMsg.add("你的胜利条件是拥有【暗杀】状态的玩家被投票出局。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 刺客。");
		nightMsg.add("显示【暗杀】状态的玩家是你使用技能的目标。");
		nightMsg.add("你的胜利条件是拥有【暗杀】状态的玩家被投票出局。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 刺客。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 刺客，你现在的身份可能已有变化。");
		dayMsg.add("显示【暗杀】状态的玩家是你使用技能的目标，其当前状态不一定为【暗杀】。");
		dayMsg.add("若你依然是刺客，你的胜利条件是拥有【暗杀】状态的玩家被投票出局。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 刺客，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.ASSASSINATION);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.ASSASSINATION) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Assassination());
			}
		}
	}
	
	public boolean win(boolean f) {
		for (int i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getCurrentStatus().getNum() == Consts.ASSASSINATION) {
				if (p.isVotedOut()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
