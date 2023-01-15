package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Drunk extends Role{
	public Drunk() {
		super();
		roleNum = 7;
		side = Consts.HUMAN;
		img = "r07";
		sequence = 800;
		name = "酒鬼";
		chooseCenterNum = 1;
		hasNight = true;
		mandatory = true;
		nightMsg.add("你的初始身份是 酒鬼。");
		nightMsg.add("选择一张中央身份牌和你的角色牌交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 酒鬼。");
		confirmedMsg.add("拥有交换标记的中央身份是你选择的交换对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 酒鬼，你现在的身份已有变化。");
		dayMsg.add("拥有交换标记的中央身份是你选择的交换对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 酒鬼，你现在的身份已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			player.getCenterMarks().set(x, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.EXCHANGE) {
				Manipulations.swapCenterRole(player, board, i);
				player.getCenterMarks().set(i, Consts.EXCHANGE);
				break;
			}
		}
	}
	
	public void executeSkillPoisoned() {
		
	}
}
