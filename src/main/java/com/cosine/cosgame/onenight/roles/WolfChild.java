package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class WolfChild extends Role{
	public WolfChild() {
		super();
		roleNum = 24;
		side = Consts.HUMAN;
		img = "r24";
		sequence = 0;
		name = "狼孩";
		nightMsg.add("你的初始身份是 狼孩。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼孩。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼孩，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是狼孩，你收到狼阵营玩家的投票后将变成狼阵营。");
		votedMsg.add("你的初始身份是 狼孩，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是狼孩，你收到狼阵营玩家的投票后将变成狼阵。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public String getFinalImg() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			if (this.side == Consts.HUMAN) {
				return "r2400";
			} else if (this.side == Consts.WOLF) {
				return "r2401";
			}
		}
		return img;
	}
}
