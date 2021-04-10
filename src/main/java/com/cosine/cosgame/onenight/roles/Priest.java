package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.NoStatus;

public class Priest extends Role{
	public Priest() {
		super();
		roleNum = 57;
		side = Consts.HUMAN;
		img = "r57";
		sequence = -200;
		name = "牧师";
		chooseStatusNum = 1;
		useStatus = true;
		hasDusk = true;
		duskMsg.add("你的身份是 牧师。");
		duskMsg.add("你的状态会被清除，你可以清除一名其他玩家的状态。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 牧师。");
		nightMsg.add("你现在的状态是【没有】，状态显示为【没有】的玩家是你指定的目标。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 牧师。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 牧师，你现在的身份可能已有变化。");
		dayMsg.add("你现在的状态是【没有】，状态显示为【没有】的玩家是你指定的目标。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 牧师，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getStatusMarks().set(t1, Consts.NOSTATUS);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		Manipulations.convertStatus(player, new NoStatus());
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.NOSTATUS) {
				Manipulations.convertStatus(board.getPlayers().get(i), new NoStatus());
			}
		}
	}
}
