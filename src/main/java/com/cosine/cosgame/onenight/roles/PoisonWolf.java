package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class PoisonWolf extends Role{
	public PoisonWolf() {
		super();
		roleNum = 96;
		side = Consts.WOLF;
		img = "r96";
		sequence = -720;
		name = "淬毒狼";
		hasDusk = true;
		duskMsg.add("你的身份是 淬毒狼。");
		duskMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		duskMsg.add("你行动时将会把命运时针向后移动2位，被期间被指向过的玩家会中毒。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 淬毒狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("你行动时已把命运时针向后移动2位，被期间被指向过的玩家会中毒。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 淬毒狼。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("你行动时已把命运时针向后移动2位，被期间被指向过的玩家会中毒。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 淬毒狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("你行动时已把命运时针向后移动2位，被期间被指向过的玩家会中毒。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 淬毒狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void executeDuskSkill() {
		int x = player.getCurrentClockIndex();
		int y = board.getClockIndex();
		board.setClockIndex(x);
		board.moveClockIndex();
		int x1 = board.getClockIndex();
		if (x1>=0 && x1<board.getPlayers().size()) {
			board.getPlayers().get(x1).poison();
		}
		board.moveClockIndex();
		int x2 = board.getClockIndex();
		if (x2>=0 && x2<board.getPlayers().size()) {
			board.getPlayers().get(x2).poison();
		}
		board.setClockIndex(y);
	}
	
	public void alterClockIndex() {
		board.moveClockIndex();
		board.moveClockIndex();
	}
}
