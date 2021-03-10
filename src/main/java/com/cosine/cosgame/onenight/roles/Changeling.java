package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Changeling extends Role{
	public Changeling() {
		super();
		roleNum = 48;
		side = Consts.HUMAN;
		img = "r48";
		sequence = 670;
		name = "调换儿";
		chooseCenterNum = 1;
		hasNight = true;
		mandatory = true;
		nightMsg.add("你的初始身份是 调换儿。");
		nightMsg.add("将你的身份和一张中央身份交换，黎明之前你的身份与该位置的中央身份将再次被交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，天亮时你可以看到你获得的身份牌。");
		confirmedMsg.add("你的初始身份是 调换儿。");
		confirmedMsg.add("拥有交换标记的中央身份你选择的交换对象，黎明之前你的身份和该中央身份将再次被交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 调换儿，你现在的身份可能已有变化。");
		dayMsg.add("拥有交换标记的中央身份是你当时的交换对象，黎明之前你的身份和该中央身份再次交换。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 调换儿，你现在的身份可能已有变化。");
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
				Manipulations.viewCurrentRole(player);
				break;
			}
		}
	}
	
	public void onDawnSkill() {
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.EXCHANGE) {
				Manipulations.swapCenterRole(player, board, i);
				break;
			}
		}
	}
}
