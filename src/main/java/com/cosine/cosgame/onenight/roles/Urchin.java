package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Urchin extends Role{
	public Urchin() {
		super();
		roleNum = 4;
		side = Consts.HUMAN;
		img = "r04";
		sequence = 700;
		name = "熊孩子";
		choosePlayerNum = 2;
		hasNight = true;
		mandatory = true;
		nightMsg.add("你的初始身份是 熊孩子。");
		nightMsg.add("选择两名其他玩家的身份进行交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 熊孩子。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 熊孩子，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时选择的交换对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 熊孩子，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
		if (t2 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t2, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int t1 = -1;
		int t2 = -1;
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				if (t1 == -1) {
					t1 = i;
				} else {
					t2 = i;
				}
			}
		}
		if (t1 != -1 && t2 != -1) {
			Player p1 = board.getPlayers().get(t1);
			Player p2 = board.getPlayers().get(t2);
			Role r1 = p1.getCurrentRole();
			Role r2 = p2.getCurrentRole();
			p2.addRole(r1);
			p1.addRole(r2);
		}
	}
	
}
