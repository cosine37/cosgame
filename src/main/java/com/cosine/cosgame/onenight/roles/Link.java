package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Link extends Role{
	public Link() {
		super();
		roleNum = 52;
		side = Consts.HUMAN;
		img = "r52";
		sequence = 0;
		name = "塞尔达";
		nightMsg.add("你的初始身份是 塞尔达。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 塞尔达。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 塞尔达，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是塞尔达，你投票的玩家将收获2票而不是1票。其两侧的玩家也将分别收获1票。");
		dayMsg.add("若你依然是塞尔达，投你的玩家将收获1票。");
		votedMsg.add("你的初始身份是 塞尔达，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是塞尔达，你投票的玩家将收获2票而不是1票。其两侧的玩家也将分别收获1票。");
		votedMsg.add("若你依然是塞尔达，投你的玩家将收获1票。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public int voteValue() {
		return 2;
	}
	
	public void voteHandle() {
		super.voteHandle();
		int x = player.getVoteIndex();
		if (x>=0 && x<=board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			Player pp = p.prevPlayer();
			Player np = p.nextPlayer();
			pp.receiveVote(1);
			np.receiveVote(1);
		}
	}
	
	public void votedReaction(Player p) {
		super.votedReaction(p);
		p.receiveVote(1);
	}

}
