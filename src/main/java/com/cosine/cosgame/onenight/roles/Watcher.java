package com.cosine.cosgame.onenight.roles;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Watcher extends Role{
	public Watcher() {
		super();
		roleNum = 18;
		side = Consts.HUMAN;
		img = "r18";
		sequence = 950;
		name = "观者";
		hasNight = true;
		nightMsg.add("你的初始身份是 观者。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到初始身份为预言家和见习预言家的玩家的最终身份。");
		confirmedMsg.add("你的初始身份是 观者。");
		confirmedMsg.add("天亮时你可以看到初始身份为预言家和见习预言家的玩家的最终身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 观者");
		dayMsg.add("场上显示的身份牌为初始身份是预言家和见习预言家的玩家的最终身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 观者。");
		votedMsg.add("场上显示的身份牌为初始身份是预言家和见习预言家的玩家的最终身份。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		Manipulations.viewFinalRole(player, board, Consts.SEER);
		Manipulations.viewFinalRole(player, board, Consts.APPRENTICESEER);
	}
	
	public void executeSkillPoisoned() {
		int i,x,y,n,z;
		int seerRole = -99999;
		int apprenticeSeerRole = -1;
		boolean hasSeer = false;
		boolean hasApprenticeSeer = false;
		List<Role> allRoles = board.getRolesThisGame();
		for (i=0;i<allRoles.size();i++) {
			if (allRoles.get(i).getRoleNum() == Consts.SEER) {
				hasSeer = true;
			}
			if (allRoles.get(i).getRoleNum() == Consts.APPRENTICESEER) {
				hasApprenticeSeer = true;
			}
		}
		Random rand = new Random();
		n = board.getPlayers().size();
		if (hasSeer) {
			x = rand.nextInt(n+2);
			if (x<3) {
				hasSeer = false;
			}
		}
		if (hasApprenticeSeer) {
			x = rand.nextInt(n+2);
			if (x<3) {
				hasApprenticeSeer = false;
			}
		}
		if (hasSeer) {
			x = rand.nextInt(10000);
			y = rand.nextInt(n-1);
			if (y>=player.getIndex()) {
				y = y+1;
			}
			if (x%3 < 2) {
				player.getPlayerMarks().set(y, Consts.SEER);
				seerRole = Consts.SEER;
			} else {
				z = rand.nextInt(allRoles.size());
				seerRole = allRoles.get(z).getRoleNum();
				player.getPlayerMarks().set(y, seerRole);
			}
		}
		if (hasApprenticeSeer) {
			x = rand.nextInt(10000);
			y = rand.nextInt(n-1);
			if (y>=player.getIndex()) {
				y = y+1;
			}
			if (x%3 < 2 && seerRole != Consts.APPRENTICESEER) {
				player.getPlayerMarks().set(y, Consts.APPRENTICESEER);
			} else {
				while (allRoles.size()>0) {
					z = rand.nextInt(allRoles.size());
					Role r = allRoles.remove(z);
					if (r.getRoleNum() == seerRole) {
						continue;
					}
					apprenticeSeerRole = r.getRoleNum();
					break;
				}
				player.getPlayerMarks().set(y, apprenticeSeerRole);
			}
		}
	}
}
