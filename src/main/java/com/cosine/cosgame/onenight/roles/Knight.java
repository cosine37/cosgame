package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Knight extends Role{
	public Knight() {
		super();
		roleNum = 91;
		side = Consts.HUMAN;
		img = "r91";
		sequence = 0;
		name = "骑士";
		nightMsg.add("你的初始身份是 骑士。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 骑士。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 骑士，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是骑士，你投“人”阵营玩家将会出局，否则你投的玩家将额外获得2票。");
		votedMsg.add("你的初始身份是 骑士，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是骑士，你投“人”阵营玩家将会出局，否则你投的玩家将额外获得2票。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void afterVoteCountHandle() {
		int x = player.getVoteIndex();
		if (x>=0 && x<board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			Role r = p.getCurrentRole();
			if (r.getSide() == Consts.HUMAN) {
				player.setVotedOut(true);
			} else {
				p.receiveVote(2);
			}
		}
	}

}
