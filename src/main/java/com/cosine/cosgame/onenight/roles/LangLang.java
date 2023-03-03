package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class LangLang extends Role{
	public LangLang() {
		super();
		roleNum = 84;
		side = Consts.WOLF;
		img = "r84";
		sequence = 200;
		name = "狼朗";
		hasNight = true;
		nightMsg.add("你的初始身份是 狼朗。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("黎明阶段前一些身份的结算会被跳过并直接进入黎明阶段。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼朗。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("黎明阶段前一些身份的结算会被跳过并直接进入黎明阶段。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼朗，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("黎明阶段前一些身份的结算被跳过且直接进入了黎明阶段。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼朗，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("黎明阶段前一些身份的结算被跳过且直接进入了黎明阶段。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void onDawnSkill() {
		board.setSkipOnDawn(true);
	}
}
