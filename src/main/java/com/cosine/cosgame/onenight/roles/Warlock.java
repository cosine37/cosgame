package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Confused;

public class Warlock extends Role{
	public Warlock() {
		super();
		roleNum = 63;
		side = Consts.HUMAN;
		img = "r63";
		sequence = 693;
		name = "术士";
		chooseStatusNum = 1;
		hasNight = true;
		useStatus = true;
		nightMsg.add("你的初始身份是 术士。");
		nightMsg.add("你可以交换你与另一名玩家的状态。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 术士。");
		confirmedMsg.add("状态上有标记的玩家是你交换的目标。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 术士，你现在的身份可能已有变化。");
		dayMsg.add("显示状态的玩家是你交换的目标。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 术士，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.TARGET);
		}
	}
	
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				int x = player.getCurrentStatus().getNum();
				Manipulations.swapStatuses(player, p);
				Manipulations.viewCurrentStatus(player);
				player.getStatusMarks().set(i, x);
				break;
			}
		}
	}
}
