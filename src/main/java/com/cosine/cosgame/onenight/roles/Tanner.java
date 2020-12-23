package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Tanner extends Role{
	public Tanner() {
		super();
		roleNum = 10;
		side = Consts.TANNER;
		img = "r10";
		sequence = 0;
		name = "皮匠";
		nightMsg.add("你的初始身份是 皮匠。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 皮匠。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 皮匠，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是皮匠且被公投出局，而且没有狼阵营玩家被公投出局，你获得胜利。");
		votedMsg.add("你的初始身份是 皮匠，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是皮匠且被公投出局，而且没有狼阵营玩家被公投出局，你获得胜利。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
}
