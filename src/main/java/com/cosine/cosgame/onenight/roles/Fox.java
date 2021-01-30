package com.cosine.cosgame.onenight.roles;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Fox extends Role{
	public Fox() {
		super();
		roleNum = 38;
		side = Consts.HUMAN;
		img = "r38";
		sequence = 990;
		name = "狐仙";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 狐仙。");
		nightMsg.add("你可以选择一名其他玩家，天亮时从该玩家起，从左到右的三名不包括你的玩家的最终阵营有“狼”，你的狐狸会叫。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狐仙。");
		confirmedMsg.add("身份牌上拥有标记的玩家是你选择的对象，如果你选择的玩家的最终阵营有“狼”，天亮时你的狐狸会叫。");
		confirmedMsg.add("正等待其他玩家确认。");
		votedMsg.add("你的初始身份是 狐仙，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
			Player p = board.getPlayers().get(t1);
			int markCount = 1;
			do {
				Player px = p.nextPlayer();
				if (px.getIndex() != player.getIndex()) {
					int ipx = px.getIndex();
					player.getPlayerMarks().set(ipx, Consts.TARGET);
					markCount++;
				}
				p = px;
			} while (markCount < 3 && p.getIndex() != t1);
		}
	}
	
	public boolean shouldBark() {
		for (int i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				if (p.getCurrentRole().getSide() == Consts.WOLF) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String genFoxSay() {
		String ans = "";
		Random rand = new Random();
		int n = 4;
		int x = rand.nextInt(n);
		if (x == 0) {
			ans = "叮叮叮叮叮歌林歌林";
		} else if (x == 1) {
			ans = "哇怕怕怕怕怕炮";
		} else if (x == 2) {
			ans = "哈踢哈踢哈踢吼";
		} else if (x == 3) {
			ans = "啾啾啾啾啾啾啾啾啾";
		}
		return ans;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 狐仙，你现在的身份可能已有变化。");
		if (shouldBark()) {
			dayMsg.add("你的狐狸叫道：“" + genFoxSay() + "！”你选中的目标中至少有一个玩家是“狼”阵营。");
		} else {
			dayMsg.add("你的狐狸没有叫，你选中的目标玩家都不是“狼”阵营。");
		}
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	
}
