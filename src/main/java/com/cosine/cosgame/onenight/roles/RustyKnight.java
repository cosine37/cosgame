package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class RustyKnight extends Role{
	public RustyKnight() {
		super();
		roleNum = 66;
		side = Consts.HUMAN;
		img = "r66";
		sequence = 0;
		name = "锈剑骑士";
		nightMsg.add("你的初始身份是 锈剑骑士。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 锈剑骑士。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 锈剑骑士，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是锈剑骑士，投你的玩家将出局。");
		votedMsg.add("你的初始身份是 塞尔达，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是锈剑骑士，投你的玩家将出局。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void votedReaction(Player p) {
		super.votedReaction(p);
		p.setVotedOut(true);
	}

}
