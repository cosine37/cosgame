package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Mason extends Role{
	public Mason() {
		super();
		roleNum = 8;
		side = Consts.HUMAN;
		img = "r08";
		sequence = 400;
		name = "砖瓦工";
		nightMsg.add("你的初始身份是 砖瓦工。");
		nightMsg.add("另一个砖瓦工身份已显示，若未显示，则该砖瓦工位于中央牌区。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 砖瓦工。");
		confirmedMsg.add("另一个砖瓦工身份已显示，若未显示，则该砖瓦工位于中央牌区。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 砖瓦工，你现在的身份可能已有变化。");
		dayMsg.add("另一个砖瓦工身份已显示，可能已有变化。若未显示，则该砖瓦工位于中央牌区。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 砖瓦工，你现在的身份可能已有变化。");
		votedMsg.add("另一个砖瓦工身份已显示，可能已有变化。若未显示，则该砖瓦工位于中央牌区。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getRoleNum() == Consts.MASON) {
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNum());
			}
		}
	}
	
	
}
