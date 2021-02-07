package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Pagan extends Role{
	public Pagan() {
		super();
		roleNum = 34;
		side = Consts.HUMAN;
		img = "r34";
		sequence = 0;
		name = "邪灵法师";
		nightMsg.add("你的初始身份是 邪灵法师。");
		nightMsg.add("夜晚观看你身份牌的玩家身份将立即变成“狼人”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 邪灵法师。");
		confirmedMsg.add("夜晚观看你身份牌的玩家身份将立即变成“狼人”。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 邪灵法师。");
		dayMsg.add("夜晚观看你身份牌的玩家身份已立即变成“狼人”。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 邪灵法师。");
		votedMsg.add("夜晚观看你身份牌的玩家身份已立即变成“狼人”。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void onView(Player viewer) {
		Role r1 = new QuoteWerewolf();
		Manipulations.convertRole(viewer, r1);
		viewer.setShowUpdatedRole(true);
	}
}
