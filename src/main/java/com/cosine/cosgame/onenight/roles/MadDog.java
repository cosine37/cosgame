package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Role;

public class MadDog extends Role{
	public MadDog() {
		super();
		roleNum = 67;
		side = Consts.HUMAN;
		img = "r67";
		sequence = 0;
		name = "疯狗";
		nightMsg.add("你的初始身份是 疯狗。");
		nightMsg.add("该身份的初始阵营为“人”。若该身份牌被交换，则该身份的阵营变为“狼”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 疯狗。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("该身份的初始阵营为“人”。若该身份牌被交换，则该身份的阵营变为“狼”。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 疯狗，你现在的身份可能已有变化。");
		dayMsg.add("该身份的初始阵营为“人”。若该身份牌被交换，则该身份的阵营变为“狼”。");
		dayMsg.add("若你的身份依然是 疯狗，投票结束后你获得的票数将变为0。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是  疯狗，你现在的身份可能已有变化。");
		votedMsg.add("若你的身份依然是 疯狗，投票结束后你获得的票数将变为0。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public String getDBStorageImg() {
		if (this.side == Consts.HUMAN) {
			return "r6700";
		} else if (this.side == Consts.WOLF) {
			return "r6701";
		} else {
			return "r67";
		}
	}
	
	public String getFinalImg() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			if (this.side == Consts.HUMAN) {
				return "r6700";
			} else if (this.side == Consts.WOLF) {
				return "r6701";
			}
		}
		return img;
	}
	
	public void onExchange() {
		this.side = Consts.WOLF;
	}
	
	public void afterVoteCountHandle() {
		player.setNumVotes(0);
	}
}
