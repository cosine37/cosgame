package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Nun extends Role{
	public Nun() {
		super();
		roleNum = 51;
		side = Consts.HUMAN;
		img = "r51";
		sequence = 855;
		name = "尼姑";
		hasNight = true;
		nightMsg.add("你的初始身份是 尼姑。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮前你的身份牌会和尼姑身份牌发生交换。");
		confirmedMsg.add("你的初始身份是 尼姑。");
		confirmedMsg.add("天亮前你的身份牌会和尼姑身份牌发生交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 尼姑，现在很有可能也是尼姑。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 尼姑，现在很有可能也是尼姑。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getCurrentRole().getRoleNum() == Consts.NUN) {
				Manipulations.swapRoles(player, p);
			}
		}
		for (i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			if (r.getRoleNum() == Consts.NUN) {
				Manipulations.swapCenterRole(player, board, i);
			}
		}
	}
	
	
}
