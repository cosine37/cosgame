package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Role;

public class Sorcerer extends Role{
	public Sorcerer() {
		super();
		roleNum = 42;
		side = Consts.HUMAN;
		img = "r42";
		sequence = 575;
		name = "灵媒";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 灵媒。");
		nightMsg.add("游戏开始时中央身份没有预言家或见习预言家。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 灵媒。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 灵媒，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时所看到的身份牌，可能已有变化。");
		dayMsg.add("若没有显示身份牌，则游戏开始时中央身份有预言家或见习预言家。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 灵媒，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	boolean hasSeerInMiddle() {
		boolean flag = false;
		int i;
		for (i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			if (r.getRoleNum() == Consts.SEER || r.getRoleNum() == Consts.APPRENTICESEER) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public void useSkill(int t1) {
		if (hasSeerInMiddle() && t1 < board.getPlayers().size()) {
			Manipulations.viewPlayerRole(player, board.getPlayers().get(t1));
		}
	}
	
	public int getChoosePlayerNum() {
		if (hasSeerInMiddle()) {
			choosePlayerNum = 1;
		} else {
			choosePlayerNum = 0;
		}
		return choosePlayerNum;
	}
	
	public List<String> getNightMsg() {
		if (hasSeerInMiddle()) {
			nightMsg = new ArrayList<>();
			nightMsg.add("你的初始身份是 灵媒。");
			nightMsg.add("游戏开始时中央身份有预言家或见习预言家，你可以选择一名其它玩家的身份牌查看。");
			nightMsg.add("选择后点击确定结束你的夜晚阶段。");
		}
		return nightMsg;
	}
	
}
