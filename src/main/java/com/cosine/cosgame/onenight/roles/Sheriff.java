package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Sheriff extends Role{
	public Sheriff() {
		super();
		roleNum = 20;
		side = Consts.HUMAN;
		img = "r20";
		sequence = 0;
		name = "警长";
		nightMsg.add("你的初始身份是 警长。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 警长。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 警长，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是警长，你投票的玩家将收获2票而不是1票。");
		votedMsg.add("你的初始身份是 警长，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是警长，你投票的玩家将收获2票而不是1票。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
}
