package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class YoungWitch extends Role{
	public YoungWitch() {
		super();
		roleNum = 21;
		side = Consts.HUMAN;
		img = "r21";
		sequence = 690;
		name = "小魔女";
		choosePlayerNum = 1;
		chooseCenterNum = 1;
		hasNight = true;
		mandatory = true;
		canChooseBoth = true;
		nightMsg.add("你的初始身份是 小魔女。");
		nightMsg.add("选择一张中央身份和一名其他玩家的身份进行交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 小魔女。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 小魔女，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时选择的交换对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 小魔女，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			player.getPlayerMarks().set(t2, Consts.EXCHANGE);
			player.getCenterMarks().set(x, Consts.EXCHANGE);
		} else {
			int x = t2-100;
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
			player.getCenterMarks().set(x, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int t1 = -1;
		int t2 = -1;
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				t1 = i;
				break;
			}
		}
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.EXCHANGE) {
				t2 = i;
				break;
			}
		}
		if (t1 != -1 && t2 != -1) {
			Player p1 = board.getPlayers().get(t1);
			Role r1 = p1.getCurrentRole();
			Role r2 = board.getCurCenterRole(t2);
			if (r1.exchangable() && r2.exchangable()) {
				p1.addRole(r2);
				board.addCenterRole(t2, r1);
			}
		}
	}
	
}
