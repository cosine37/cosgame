package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Confused;

public class Gremlin extends Role{
	public Gremlin() {
		super();
		roleNum = 64;
		side = Consts.HUMAN;
		img = "r64";
		sequence = 790;
		name = "小魔怪";
		choosePlayerNum = 2;
		chooseStatusNum = 2;
		hasNight = true;
		useStatus = true;
		mandatory = true;
		nightMsg.add("你的初始身份是 小魔怪。");
		nightMsg.add("交换两名其他玩家的身份或状态状态。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 小魔怪。");
		confirmedMsg.add("身份或状态上有标记的玩家是你交换的目标。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 小魔怪，你现在的身份可能已有变化。");
		dayMsg.add("身份或状态上有标记的玩家是你交换的目标。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 小魔怪，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 >= 200 && t2 >= 200) {
			t1 = t1-200;
			t2 = t2-200;
			if (t1 < board.getPlayers().size()) {
				player.getStatusMarks().set(t1, Consts.TARGET);
			}
			if (t2 < board.getPlayers().size()) {
				player.getStatusMarks().set(t2, Consts.TARGET);
			}
		} else {
			if (t1 < board.getPlayers().size()) {
				player.getPlayerMarks().set(t1, Consts.EXCHANGE);
			}
			if (t2 < board.getPlayers().size()) {
				player.getPlayerMarks().set(t2, Consts.EXCHANGE);
			}
		}
		
	}
	
	
	public void executeSkill() {
		int t1 = -1;
		int t2 = -1;
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				if (t1 == -1) {
					t1 = i;
				} else {
					t2 = i;
				}
			}
		}
		if (t1 != -1 && t2 != -1) {
			Player p1 = board.getPlayers().get(t1);
			Player p2 = board.getPlayers().get(t2);
			Manipulations.swapRoles(p1, p2);
			return;
		}
		t1 = -1;
		t2 = -2;
		for (i=0;i<player.getStatusMarks().size();i++) {
			if (player.getStatusMarks().get(i) == Consts.TARGET) {
				if (t1 == -1) {
					t1 = i;
				} else {
					t2 = i;
				}
			}
		}
		if (t1 != -1 && t2 != -1) {
			Player p1 = board.getPlayers().get(t1);
			Player p2 = board.getPlayers().get(t2);
			Manipulations.swapStatuses(p1, p2);
			return;
		}
	}
}
