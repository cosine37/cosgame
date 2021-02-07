package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class MysticWolf extends Role{
	public MysticWolf() {
		super();
		roleNum = 15;
		side = Consts.WOLF;
		img = "r15";
		sequence = 250;
		name = "狼先知";
		choosePlayerNum = 1;
		canChooseBoth = true;
		hasNight = true;
		nightMsg.add("你的初始身份是 狼先知。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("你可以查看一名其他玩家的身份牌。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼先知。");
		confirmedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你夜晚观看的玩家。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼先知，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你夜晚观看的玩家，但当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼先知，你现在的身份可能已有变化。");
		votedMsg.add("场上显示身份牌的玩家为初始身份和你同一阵营或为你夜晚观看的玩家，但当前身份可能已有变化。");
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
	}
	
	boolean isSoleWolf() {
		if (board.isSoleWolf()) {
			int i;
			int t=0;
			for (i=0;i<board.getPlayers().size();i++) {
				if (player.getIndex() == i) {
					continue;
				}
				Player p = board.getPlayers().get(i);
				if (p.getInitialRole().getSide() == Consts.WOLF && p.getInitialRole().getRoleNum() != Consts.MINION) {
					t++;
				}
			}
			if (t == 0) {
				return true;
			}
		}
		return false;
	}
	
	public void useSkill(int t1) {
		if (isSoleWolf()) {
			if (t1 >= 100 && t1 <= 102) {
				int x = t1-100;
				int y = board.getCurCenterRole(x).getRoleNum();
				player.getCenterMarks().set(x, y);
			} else {
				if (t1>=0 && t1 < board.getPlayers().size()) {
					Player p = board.getPlayers().get(t1);
					Manipulations.viewPlayerRole(player, p);
				}
			}
		} else {
			if (t1>=0 && t1 < board.getPlayers().size()) {
				Player p = board.getPlayers().get(t1);
				Manipulations.viewPlayerRole(player, p);
			}
		}
	}
	
	public void useSkill(int t1, int t2) {
		if (isSoleWolf()) {
			if (t1 >= 100 && t1 <= 102) {
				int x = t1-100;
				int y = board.getCurCenterRole(x).getRoleNum();
				player.getCenterMarks().set(x, y);
				x = board.getPlayers().get(t2).getCurrentRole().getRoleNum();
				player.getPlayerMarks().set(t2, x);
			} else {
				int x = t2-100;
				int y = board.getCurCenterRole(x).getRoleNum();
				player.getCenterMarks().set(x, y);
				x = board.getPlayers().get(t1).getCurrentRole().getRoleNum();
				player.getPlayerMarks().set(t1, x);
			}
		}
	}
	
	public int getChooseCenterNum() {
		if (isSoleWolf()) {
			chooseCenterNum = 1;
		}
		return chooseCenterNum;
	}
	
}
