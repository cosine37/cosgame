package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Seer extends Role{
	public Seer() {
		super();
		roleNum = 2;
		side = Consts.HUMAN;
		img = "r02";
		sequence = 500;
		name = "预言家";
		choosePlayerNum = 1;
		chooseCenterNum = 2;
		hasNight = true;
		nightMsg.add("你的初始身份是 预言家。");
		nightMsg.add("你可以选择一名其它角色的身份牌或两张中央身份牌查看。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，之后你可以看到你选择的身份牌。");
		confirmedMsg.add("你的初始身份是 预言家。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 预言家，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时所看到的身份牌，可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 预言家，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (player.isPoisoned()) {
			useSkillPoisoned(t1);
			return;
		}
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			Manipulations.viewPlayerRole(player, p);
		}
	}
	
	public void useSkillPoisoned(int t1) {
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			Manipulations.viewPlayerRolePoisoned(player, p);
		}
	}
	
	public void useSkill(int t1, int t2) {
		if (player.isPoisoned()) {
			useSkillPoisoned(t1, t2);
			return;
		}
		Manipulations.viewCenterRoles(player, board, t1, t2);
	}
	
	public void useSkillPoisoned(int t1, int t2) {
		Manipulations.viewCenterRolesPoisoned(player, board, t1, t2);
	}
}
