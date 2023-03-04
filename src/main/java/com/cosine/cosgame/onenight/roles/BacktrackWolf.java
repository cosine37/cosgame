package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class BacktrackWolf extends Role{
	public BacktrackWolf() {
		super();
		roleNum = 88;
		side = Consts.WOLF;
		img = "r88";
		sequence = -1000;
		name = "时刻狼";
		hasDusk = true;
		duskMsg.add("你的身份是 时刻狼。");
		duskMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		duskMsg.add("本局游戏命运时针向左（逆时针）移动。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 时刻狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("本局游戏命运时针向左（逆时针）移动。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 时刻狼。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("本局游戏命运时针向左（逆时针）移动。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 时刻狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("本局游戏命运时针向左（逆时针）移动。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 时刻狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("本局游戏命运时针向左（逆时针）移动。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void alterClockIndex() {
		board.setClockwise(false);
	}
}
