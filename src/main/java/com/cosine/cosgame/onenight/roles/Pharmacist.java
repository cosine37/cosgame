package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;
import com.cosine.cosgame.onenight.statuses.Love;

public class Pharmacist extends Role{
	public Pharmacist() {
		super();
		roleNum = 82;
		side = Consts.HUMAN;
		img = "r82";
		sequence = -700;
		name = "药剂师";
		choosePlayerNum = 2;
		hasDusk = true;
		canBePoisoned = false;
		duskMsg.add("你的身份是 药剂师。");
		duskMsg.add("你对中毒免疫，指定两名其他玩家，若其中毒，为其解毒。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 药剂师。");
		nightMsg.add("你对中毒免疫。身份牌上有标记的玩家是被你解毒的玩家。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 药剂师。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 药剂师，你现在的身份可能已有变化。");
		dayMsg.add("你对中毒免疫。身份牌上有标记的玩家是被你解毒的玩家。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是药剂师，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		t1 = t1-200;
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
		if (t2 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t2, Consts.TARGET);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				board.getPlayers().get(i).detoxify();
			}
		}
	}
}
