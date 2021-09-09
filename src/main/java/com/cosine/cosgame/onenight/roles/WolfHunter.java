package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class WolfHunter extends Role{
	public WolfHunter() {
		super();
		roleNum = 68;
		side = Consts.HUMAN;
		img = "r68";
		sequence = 0;
		name = "猎狼人";
		nightMsg.add("你的初始身份是 猎狼人。");
		nightMsg.add("天亮前该身份会显示为场上随机一个狼身份且阵营暂时为“狼”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 猎狼人。");
		confirmedMsg.add("天亮前该身份会显示为场上随机一个狼身份且阵营暂时为“狼”。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 猎狼人，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若你依然是猎狼人且被公投出局，你投票的玩家也出局。");
		votedMsg.add("你的初始身份是 猎狼人，你现在的身份可能已有变化。");
		votedMsg.add("若你依然是猎狼人且被公投出局，你投票的玩家也出局。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public int getRoleNumToShow() {
		if (board.getWolfHunterIndex() == -1) {
			return roleNum;
		}
		return board.getRolesThisGame().get(board.getWolfHunterIndex()).getRoleNum();
	}
	
	public String getFinalImg() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			return img;
		} else if (board.getWolfHunterIndex() == -1){
			return img;
		} else {
			return board.getWolfHunterImg();
		}
	}
	
	public int getSide() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			return side;
		} else {
			return Consts.WOLF;
		}
	}
	
	public int voteValue() {
		return 2;
	}
	
	
}
