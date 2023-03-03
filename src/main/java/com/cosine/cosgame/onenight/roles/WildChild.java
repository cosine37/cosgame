package com.cosine.cosgame.onenight.roles;

import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class WildChild extends Role{
	public WildChild() {
		super();
		roleNum = 89;
		side = Consts.HUMAN;
		img = "r89";
		sequence = -770;
		name = "野孩子";
		choosePlayerNum = 2;
		hasDusk = true;
		mandatory = true;
		duskMsg.add("你的身份是 野孩子。");
		duskMsg.add("指定两名其他玩家，这两名玩家都有可能中毒，黎明之前这两名玩家的身份将被交换。");
		duskMsg.add("选择后点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 野孩子。");
		nightMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象，这两名玩家可能已中毒，黎明之前这两名玩家的身份将被交换。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 野孩子。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象，这两名玩家可能已中毒，黎明之前这两名玩家的身份将被交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 野孩子，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时选择的交换对象，这两名玩家可能已中毒，黎明之前这两名玩家的身份被交换。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 野孩子，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
		if (t2 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t2, Consts.EXCHANGE);
		}
	}
	
	public void executeDuskSkill() {
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
			Random rand = new Random();
			int x = rand.nextInt(10000);
			int y = rand.nextInt(10000);
			if (x%2 == 0) {
				p1.poison();
			}
			if (y%2 == 0) {
				p2.poison();
			}
		}
	}
	
	public void onDawnSkill() {
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
		}
	}
	
}
