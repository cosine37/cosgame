package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Bishop extends Role{
	public Bishop() {
		super();
		roleNum = 31;
		side = Consts.HUMAN;
		img = "r31";
		sequence = 960;
		name = "主教";
		nightMsg.add("你的初始身份是 主教。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到初始身份为教宗的玩家的最终身份。");
		confirmedMsg.add("你的初始身份是 主教。");
		confirmedMsg.add("天亮时你可以看到初始身份为教宗的玩家的最终身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 主教");
		dayMsg.add("场上显示的身份牌为初始身份为教宗的玩家的最终身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 主教。");
		votedMsg.add("场上显示的身份牌为初始身份为教宗的玩家的最终身份。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		Manipulations.viewFinalRole(player, board, Consts.POPE);
	}
}
