package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Weremeleon extends Role{
	public Weremeleon() {
		super();
		roleNum = 39;
		side = Consts.WOLF;
		img = "r39";
		sequence = 200;
		name = "变色狼";
		nightMsg.add("你的初始身份是 变色狼。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("右侧显示的身份是游戏结束前你的身份牌显示的身份");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 变色狼。");
		confirmedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		confirmedMsg.add("右侧显示的身份是游戏结束前你的身份牌显示的身份");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 变色狼，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		dayMsg.add("右侧显示的身份是游戏结束前其他玩家看到的身份");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 变色狼，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
		votedMsg.add("右侧显示的身份是游戏结束前你的身份牌显示的身份");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF && p.getInitialRole().getRoleNum() != Consts.MINION) {
				player.getPlayerMarks().set(i, p.getInitialRole().getRoleNum());
			}
		}
		int x = board.getWeremeleonIndex();
		player.setShowUpdatedRole(true);
		if (x == -1) {
			player.setUpdatedRole(this);
		} else {
			player.setUpdatedRole(board.getRolesThisGame().get(x));
		}
		Manipulations.soleWolfHandle(player, board);
	}
	public int getRoleNumToShow() {
		return board.getRolesThisGame().get(board.getWeremeleonIndex()).getRoleNum();
	}
	
	public String getFinalImg() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			return img;
		} else if (board.getWeremeleonIndex() == -1){
			return img;
		} else {
			return board.getWeremeleonImg();
		}
	}
}
