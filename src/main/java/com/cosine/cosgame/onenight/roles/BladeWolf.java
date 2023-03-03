package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class BladeWolf extends Role{
	public BladeWolf() {
		super();
		roleNum = 74;
		side = Consts.WOLF;
		img = "r74";
		sequence = 200;
		name = "刀狼";
		hasNight = true;
		nightMsg.add("你的初始身份是 刀狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 刀狼。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 刀狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是刀狼，且投的玩家最终身份为教宗，则视为所有“狼”阵营玩家投了教宗。");
		votedMsg.add("你的初始身份是 刀狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("若你依然是刀狼，且投的玩家最终身份为教宗，则视为所有“狼”阵营玩家投了教宗。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
}
