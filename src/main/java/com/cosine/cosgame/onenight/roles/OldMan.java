package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class OldMan extends Role{
	public OldMan() {
		super();
		roleNum = 49;
		side = Consts.HUMAN;
		img = "r49";
		sequence = 910;
		name = "苦涩老头";
		hasNight = true;
		nightMsg.add("你的初始身份是 苦涩老头。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以知道最终身份是苦涩老头的玩家。");
		confirmedMsg.add("你的初始身份是 苦涩老头。");
		confirmedMsg.add("天亮时你可以知道最终身份是苦涩老头的玩家。");
		confirmedMsg.add("正等待其他玩家确认。");
	}
	
	public void executeSkill() {
		for (int i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getCurrentRole().getRoleNum() == Consts.OLDMAN) {
				if (p.getIndex() == player.getIndex()) {
					player.setShowUpdatedRole(true);
					player.setUpdatedRole(p.getCurrentRole());
				} else {
					player.getPlayerMarks().set(p.getIndex(), p.getCurrentRole().getRoleNumToShow());
				}
			}
		}
	}
	
	public void executeSkillPoisoned() {
		Random rand = new Random();
		int x = rand.nextInt(10000);
		if (x%3 < 2) {
			player.setShowUpdatedRole(true);
			player.setUpdatedRole(player.getInitialRole());
		} else {
			int i;
			List<Integer> ls = new ArrayList<>();
			for (i=0;i<board.getPlayers().size();i++) {
				ls.add(i);
			}
			while (ls.size()>0) {
				x = rand.nextInt(ls.size());
				int y = ls.remove(x);
				if (y == player.getIndex()) {
					continue;
				}
				player.getPlayerMarks().set(y, Consts.OLDMAN);
				break;
			}
		}
	}
	
	public List<String> getDayMsg() {
		if (player.isShowUpdatedRole()) {
			dayMsg.add("你的初始身份是 苦涩老头，最终身份也是苦涩老头。");
		} else {
			dayMsg.add("你的初始身份是 苦涩老头，最终身份不是苦涩老头。");
			dayMsg.add("显示身份牌的玩家最终身份是苦涩老头。");
		}
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		if (player.isShowUpdatedRole()) {
			votedMsg.add("你的初始身份是 苦涩老头，最终身份也是苦涩老头。");
		} else {
			votedMsg.add("你的初始身份是 苦涩老头，最终身份不是苦涩老头。");
			votedMsg.add("显示身份牌的玩家最终身份是苦涩老头。");
		}
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
}
