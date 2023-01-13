package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Jester extends Role{
	public Jester() {
		super();
		roleNum = 77;
		side = Consts.HUMAN;
		img = "r77";
		sequence = 589;
		name = "弄臣";
		choosePlayerNum = 1;
		chooseCenterNum = 1;
		hasNight = true;
		mandatory = true;
		canChooseBoth = true;
		nightMsg.add("你的初始身份是 弄臣。");
		nightMsg.add("查看一名玩家的身份并指定一张中央身份，黎明阶段开始前将该玩家的身份与中央身份交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 弄臣。");
		confirmedMsg.add("场上有标记的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 弄臣，你现在的身份可能已有变化。");
		dayMsg.add("场上有标记的身份牌是你发动你技能时指定的身份牌，其身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 弄臣，你现在的身份可能已有变化。");
		votedMsg.add("场上有标记的身份牌是你发动你技能时指定的身份牌，其身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			int x = t2-100;
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
			player.getCenterMarks().set(x, Consts.EXCHANGE);
			Manipulations.viewPlayerRole(player, p);
		}
	}
	
	public void onDawnSkill() {
		int t1 = -1;
		int t2 = -1;
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) != Consts.UNKNOWN) {
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
			Manipulations.swapCenterRole(p1, board, t2);
			int x1 = player.getPlayerMarks().get(i);
			int x2 = player.getCenterMarks().get(i);
			player.getPlayerMarks().set(t1, x2);
			player.getCenterMarks().set(t2, x1);
		}
	}
}
