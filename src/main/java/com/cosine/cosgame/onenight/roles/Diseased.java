package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Plague;

public class Diseased extends Role{
	public Diseased() {
		super();
		roleNum = 60;
		side = Consts.HUMAN;
		img = "r60";
		sequence = -500;
		name = "传染病人";
		useStatus = true;
		hasDusk = true;
		duskMsg.add("你的身份是 传染病人。");
		duskMsg.add("你可以给你的左侧或右侧玩家施加【瘟疫】状态。");
		duskMsg.add("投票阶段投你或拥有【瘟疫】状态的玩家无法获胜。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 传染病人。");
		nightMsg.add("显示【瘟疫】状态的玩家是你使用技能的目标。");
		nightMsg.add("投票阶段投你或拥有【瘟疫】状态的玩家无法获胜。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 传染病人。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 传染病人，你现在的身份可能已有变化。");
		dayMsg.add("显示【瘟疫】状态的玩家是你使用技能的目标，其当前状态不一定为【瘟疫】。");
		dayMsg.add("投票阶段投你或拥有【瘟疫】状态的玩家无法获胜。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 传染病人，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		int index;
		
		if (t1 == 1) {
			index = player.nextPlayer().getIndex();
			player.getStatusMarks().set(index, Consts.PLAGUE);
		} else if (t1 == 2) {
			index = player.prevPlayer().getIndex();
			player.getStatusMarks().set(index, Consts.PLAGUE);
		}
		
	}
	
	public boolean votedThis(boolean f) {
		return false;
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.PLAGUE) {
				Manipulations.convertStatus(board.getPlayers().get(i), new Plague());
			}
		}
	}
}
