package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Guard extends Role{
	public Guard() {
		super();
		roleNum = 16;
		side = Consts.HUMAN;
		img = "r16";
		sequence = 0;
		name = "守卫";
		nightMsg.add("你的初始身份是 守卫。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 守卫。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 守卫，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是守卫，你投票的玩家的票数将变为0。");
		votedMsg.add("你的初始身份是 守卫，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是守卫，你投票的玩家的票数将变为0。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
}
