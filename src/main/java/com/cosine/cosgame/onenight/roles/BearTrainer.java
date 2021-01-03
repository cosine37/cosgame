package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class BearTrainer extends Role{
	public BearTrainer() {
		super();
		roleNum = 27;
		side = Consts.HUMAN;
		img = "r27";
		sequence = 420;
		name = "驯熊师";
		choosePlayerNum = 1;
		hasNight = true;
		
	}
	
	public boolean shouldBark() {
		boolean ans = false;
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		boolean f1 = false;
		boolean f2 = false;
		if (p1.getInitialRole().getSide() == Consts.WOLF) {
			if (p1.getInitialRole().getRoleNum() != Consts.INVESTIGATOR) {
				f1 = true;
			}
		}
		if (p2.getInitialRole().getSide() == Consts.WOLF) {
			if (p2.getInitialRole().getRoleNum() != Consts.INVESTIGATOR) {
				f2 = true;
			}
		}
		if (f1 || f2) {
			ans = true;
		}
		return ans;
	}
	
	public String barkMsg() {
		String ans = "";
		Player p1 = player.prevPlayer();
		Player p2 = player.nextPlayer();
		if (shouldBark()) {
			ans="你的熊咆哮了，";
			ans = ans + p1.getName() + "和" + p2.getName() + "中至少有一位玩家的初始阵营为“狼”。";
		} else {
			ans="你的熊没有咆哮，";
			ans = ans + p1.getName() + "和" + p2.getName() + "的初始阵营都不为“狼”。";
		}
		
		return ans;
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 驯熊师。");
		nightMsg.add(barkMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 驯熊师。");
		confirmedMsg.add(barkMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		dayMsg.add("你的初始身份是 驯熊师，你现在的身份可能已有变化。");
		dayMsg.add(barkMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		votedMsg.add("你的初始身份是 驯熊师，你现在的身份可能已有变化。");
		votedMsg.add(barkMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
