package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Timberwolf extends Role{
	public Timberwolf() {
		super();
		roleNum = 26;
		side = Consts.WOLF;
		img = "r26";
		sequence = 280;
		name = "森林狼";
		choosePlayerNum = 1;
		canChooseBoth = true;
		hasNight = true;
		nightMsg.add("你的初始身份是 森林狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("你可以指定一名其他玩家，该玩家白天跳过第二和第三轮发言。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 森林狼。");
		confirmedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营的玩家。");
		confirmedMsg.add("有标记的玩家是你指定的目标。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 森林狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营的玩家，但当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 森林狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营的玩家，但当前身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		Manipulations.wolfVision(player, board);
		Manipulations.soleWolfHandle(player, board);
	}
	
	public void useSkill(int t1) {
		if (t1>=0 && t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
			board.setRestrictedIndex(t1);
		}
	}
	
	
}
