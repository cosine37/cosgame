package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Magician extends Role{
	public Magician() {
		super();
		roleNum = 23;
		side = Consts.HUMAN;
		img = "r23";
		sequence = 620;
		name = "魔术师";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 魔术师。");
		nightMsg.add("你可以将你的身份和一名其他玩家的身份交换，你也可以不交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 魔术师。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 魔术师，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时的交换对象，其身份现在可能不是魔术师。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 魔术师，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				Player p = board.getPlayers().get(i);
				Manipulations.swapRoles(player, p);
			}
		}
	}
	
	
}
