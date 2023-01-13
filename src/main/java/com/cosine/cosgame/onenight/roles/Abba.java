package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Abba extends Role{
	public Abba() {
		super();
		roleNum = 76;
		side = Consts.HUMAN;
		img = "r76";
		sequence = 820;
		name = "神父";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 神父。");
		nightMsg.add("指定一名其他玩家，将其身份变为村民。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 神父。");
		confirmedMsg.add("场上有标记的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 神父，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的村民身份牌是你发动你技能时指定的身份牌，其身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 神父，你现在的身份可能已有变化。");
		votedMsg.add("场上显示的村民身份牌是你发动你技能时指定的身份牌，其身份可能已有变化。");
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
				Role r1 = new Villager();
				Manipulations.convertRole(p, r1);
				break;
			}
		}
	}
}
