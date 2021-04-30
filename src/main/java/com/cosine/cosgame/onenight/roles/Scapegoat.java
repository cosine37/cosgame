package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Scapegoat extends Role{
	public Scapegoat() {
		super();
		roleNum = 43;
		side = Consts.HUMAN;
		img = "r43";
		sequence = 930;
		name = "替罪羊";
		hasNight = true;
		nightMsg.add("你的初始身份是 替罪羊。");
		nightMsg.add("点击确认结束你的夜晚阶段。天亮时你将看到所有中央身份。");
		confirmedMsg.add("你的初始身份是 替罪羊。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 替罪羊，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是替罪羊且有玩家是狼阵营，你将额外获得2票。");
		votedMsg.add("你的初始身份是 替罪羊，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是替罪羊且有玩家是狼阵营，你将额外获得2票。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		for (int i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			System.out.println(r.getRoleNumToShow());
			player.getCenterMarks().set(i, r.getRoleNumToShow());
		}
	}
	
	public void afterVoteCountHandle() {
		int i;
		boolean flag = false;
		for (i=0;i<board.getPlayers().size();i++) {
			Role r = board.getPlayers().get(i).getCurrentRole();
			if (r.getSide() == Consts.WOLF) {
				flag = true;
				break;
			}
		}
		if (flag) {
			player.receiveVote(2);
		}
	}
}
