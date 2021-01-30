package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class LittleGirl extends Role{
	public LittleGirl() {
		super();
		roleNum = 37;
		side = Consts.HUMAN;
		img = "r37";
		sequence = 695;
		name = "小女孩";
		hasNight = true;
		nightMsg.add("你的初始身份是 小女孩。");
		nightMsg.add("与你相邻的两名玩家的身份牌将会被交换。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 小女孩。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是交换对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 小女孩，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是交换对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 小女孩，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		int t1 = p1.getIndex();
		int t2 = p2.getIndex();
		player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		player.getPlayerMarks().set(t2, Consts.EXCHANGE);
		Role r1 = p1.getCurrentRole();
		Role r2 = p2.getCurrentRole();
		if (r1.exchangable() && r2.exchangable()) {
			p2.addRole(r1);
			p1.addRole(r2);
		}
	}
	
}
