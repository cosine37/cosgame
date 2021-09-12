package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Parent extends Role{
	public Parent() {
		super();
		roleNum = 73;
		side = Consts.HUMAN;
		img = "r73";
		sequence = 970;
		name = "操心的家长";
		hasNight = true;
		nightMsg.add("你的初始身份是 操心的家长。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到初始身份为熊孩子、小女孩和小魔怪的的玩家的最终身份。");
		confirmedMsg.add("你的初始身份是 操心的家长。");
		confirmedMsg.add("天亮时你可以看到初始身份为熊孩子、小女孩和小魔怪的的玩家的最终身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 操心的家长");
		dayMsg.add("场上显示的身份牌为初始身份是熊孩子、小女孩和小魔怪的玩家的最终身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 操心的家长。");
		votedMsg.add("场上显示的身份牌为初始身份是熊孩子、小女孩和小魔怪的的玩家的最终身份。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		Manipulations.viewFinalRole(player, board, Consts.URCHIN);
		Manipulations.viewFinalRole(player, board, Consts.LITTLEGIRL);
		Manipulations.viewFinalRole(player, board, Consts.GREMLIN);
	}
}
