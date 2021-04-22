package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Confused;

public class CombatMedic extends Role{
	public CombatMedic() {
		super();
		roleNum = 62;
		side = Consts.HUMAN;
		img = "r62";
		sequence = 585;
		name = "退伍军医";
		chooseStatusNum = 1;
		choosePlayerNum = 1;
		canChooseBoth = true;
		hasNight = true;
		useStatus = true;
		nightMsg.add("你的初始身份是 退伍军医。");
		nightMsg.add("你可以查看一名玩家的身份和另一名玩家的状态。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 退伍军医。");
		confirmedMsg.add("显示身份和状态的玩家是你查看的目标。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 退伍军医，你现在的身份可能已有变化。");
		dayMsg.add("显示身份和状态的玩家是你查看的目标。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 退伍军医，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 >=200) {
			t1 = t1-200;
			if (t1 < board.getPlayers().size()) {
				Player p = board.getPlayers().get(t1);
				Manipulations.viewPlayerStatus(player, p);
			}
		} else {
			if (t1 < board.getPlayers().size()) {
				Player p = board.getPlayers().get(t1);
				Manipulations.viewPlayerRole(player, p);
			}
		}
	}
	
	public void useSkill(int t1, int t2) {
		useSkill(t1);
		useSkill(t2);
	}
}
