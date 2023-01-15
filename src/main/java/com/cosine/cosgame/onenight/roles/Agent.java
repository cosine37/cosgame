package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Agent extends Role{
	public Agent() {
		super();
		roleNum = 22;
		side = Consts.HUMAN;
		img = "r22";
		sequence = 1050;
		name = "侦探助理";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 侦探助理。");
		nightMsg.add("你可以选择其他一名玩家，天亮时你可以看到该玩家的最终身份。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 侦探助理。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 侦探助理，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 侦探助理，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				Manipulations.viewPlayerRole(player, p);
				break;
			}
		}
	}
	
	public void executeSkillPoisoned() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				Manipulations.viewPlayerRolePoisoned(player, p);
				break;
			}
		}
	}
	
	
}
