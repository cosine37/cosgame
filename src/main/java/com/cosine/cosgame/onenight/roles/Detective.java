package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Detective extends Role{
	public Detective() {
		super();
		roleNum = 13;
		side = Consts.HUMAN;
		img = "r13";
		sequence = 1000;
		name = "侦探";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 侦探。");
		nightMsg.add("你可以选择其他一名玩家，天亮时你可以看到该玩家的最终身份。");
		nightMsg.add("若该玩家的最终阵营为人，该玩家的身份将公开。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 侦探。");
		confirmedMsg.add("身份牌上拥有侦探标记的玩家是你选择的对象。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 侦探，你现在的身份可能已有变化。");
		dayMsg.add("若该玩家身份牌上有侦探标记，该玩家的身份已公开。");
		dayMsg.add("身份牌上拥有侦探标记的玩家是你当时选择的对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 侦探，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		int x = -1;
		Role r = new Role();
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				r = p.getCurrentRole();
				x = i;
				player.getPlayerMarks().set(i, r.getRoleNum());
				if (r.getRoleNum() == Consts.PAGAN) {
					Role r1 = new QuoteWerewolf();
					player.addRole(r1);
					player.setUpdatedRole(r1);
					player.setShowUpdatedRole(true);
				}
				break;
			}
		}
		if (x != -1) {
			if (r.getSide() == Consts.HUMAN && r.getRoleNum() != Consts.POPE) {
				board.setDetectiveIndex(x);
				board.setDetectiveRoleImg(r.getImg());
				Player p = board.getPlayers().get(x);
				p.setUpdatedRole(r);
				p.setShowUpdatedRole(true);
			}
		}
	}
	
	
}
