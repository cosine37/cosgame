package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Canis;

public class MushroomFarmer extends Role{
	public MushroomFarmer() {
		super();
		roleNum = 81;
		side = Consts.HUMAN;
		img = "r81";
		sequence = -900;
		name = "菇农";
		hasDusk = true;
		duskMsg.add("你的身份是 菇农。");
		duskMsg.add("与你相邻的玩家若为“人”阵营则中毒。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 菇农。");
		nightMsg.add("与你相邻的玩家若为“人”阵营则中毒，但可能被解毒。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 菇农。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 菇农，你现在的身份可能已有变化。");
		dayMsg.add("与你相邻的玩家若为“人”阵营则中毒，但可能被解毒。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 菇农，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeDuskSkill() {
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		p1.poison();
		p2.poison();
		int t1 = p1.getIndex();
		int t2 = p2.getIndex();
		player.getPlayerMarks().set(t1, Consts.POISONED);
		player.getPlayerMarks().set(t2, Consts.POISONED);
	}
}
