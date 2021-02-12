package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Prince extends Role{
	public Prince() {
		super();
		roleNum = 19;
		side = Consts.HUMAN;
		img = "r19";
		sequence = 0;
		name = "王子";
		nightMsg.add("你的初始身份是 王子。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 王子。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 王子，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是王子，你的投票无效且你不会获得任何票数。");
		votedMsg.add("你的初始身份是 王子，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是王子，你的投票无效且你不会获得任何票数。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public int voteValue() {
		return 0;
	}
	
	public void afterVoteCountHandle() {
		player.setNumVotes(0);
	}
}
