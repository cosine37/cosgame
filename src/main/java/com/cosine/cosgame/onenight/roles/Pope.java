package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Pope extends Role{
	public Pope() {
		super();
		roleNum = 30;
		side = Consts.HUMAN;
		img = "r30";
		sequence = 401;
		name = "教宗";
		hasNight = true;
		nightMsg.add("你的初始身份是 教宗。");
		nightMsg.add("身份牌上有标记的玩家初始阵营为狼。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 教宗。");
		confirmedMsg.add("身份牌上有标记的玩家初始阵营为狼。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 教宗，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上有标记的玩家初始身份和你同一阵营，当前身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		dayMsg.add("若所有狼阵营玩家将票投给你，则人阵营玩家会直接失败。");
		votedMsg.add("你的初始身份是 教宗，你现在的身份可能已有变化。");
		votedMsg.add("身份牌上有标记的玩家初始身份和你同一阵营，当前身份可能已有变化。");
		votedMsg.add("若所有狼阵营玩家将票投给你，则人阵营玩家会直接失败。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF) {
				player.getPlayerMarks().set(i, Consts.WOLFMARK);
			}
		}
	}
	
	public void visionPoisoned() {
		int count = 0;
		int i;
		for (i=0;i<board.getRolesThisGame().size();i++) {
			Role r = board.getRolesThisGame().get(i);
			if (r.getSide() == Consts.WOLF) {
				count++;
			}
		}
		List<Integer> ls = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			ls.add(i);
		}
		Random rand = new Random();
		int n = rand.nextInt(count+1);
		for (i=0;i<n;i++) {
			int x = rand.nextInt(ls.size());
			int y = ls.remove(x);
			if (y == player.getIndex()) {
				i--;
				continue;
			}
			player.getPlayerMarks().set(y, Consts.WOLFMARK);
		}
	}
}
