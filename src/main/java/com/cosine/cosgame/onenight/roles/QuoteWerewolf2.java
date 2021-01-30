package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class QuoteWerewolf2 extends Role{
	public QuoteWerewolf2() {
		super();
		roleNum = 3601;
		side = Consts.WOLF;
		img = "r3601";
		name = "“狼人”";
		nightMsg.add("你的初始身份是 狼人。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼人。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼人，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼人，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
}
