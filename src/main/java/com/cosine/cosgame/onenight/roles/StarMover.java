package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class StarMover extends Role{
	public StarMover() {
		super();
		roleNum = 95;
		side = Consts.HUMAN;
		img = "r95";
		sequence = 630;
		name = "移星师";
		hasNight = true;
		nightMsg.add("你的初始身份是 移星师。");
		nightMsg.add("你的身份牌将会和你行动时命运时针指向的玩家交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，天亮时你可以看到你获得的身份牌。");
		confirmedMsg.add("你的初始身份是 移星师。");
		confirmedMsg.add("你的身份牌将会和你行动时命运时针指向的玩家交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 移星师，你现在的身份可能已有变化。");
		dayMsg.add("你初始身份右侧的身份是你与当时命运时针所指向的玩家交换获得的身份，可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 移星师，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		int x = player.getCurrentClockIndex();
		if (x>=0 && x<board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			Manipulations.swapRoles(player, p);
			Manipulations.viewCurrentRole(player);
		}
	}
	
	public void executeSkillPoisoned() {
		Manipulations.viewCurrentRolePoisoned(player);
	}
	
	
}
