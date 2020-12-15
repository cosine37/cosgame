package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Seer extends Role{
	public Seer() {
		super();
		roleNum = 2;
		side = Consts.HUMAN;
		img = "r02";
		sequence = 500;
		name = "预言家";
		choosePlayerNum = 1;
		chooseCenterNum = 2;
		hasNight = true;
		nightMsg.add("你的初始身份是 预言家。");
		nightMsg.add("你可以选择一名其它角色的身份牌或两张中央身份牌查看。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，天亮时你可以看到你选择的身份牌。");
		dayMsg.add("你的初始身份是 预言家，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时所看到的身份牌，可能已有变化。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 >= 100 && t1 <= 102) {
			int x = t1-100;
			player.getCenterMarks().set(x, Consts.TARGET);
		}
		if (t2 >= 100 && t2 <= 102) {
			int x = t2-100;
			player.getCenterMarks().set(x, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				int x = board.getPlayers().get(i).getCurrentRole().getRoleNum();
				player.getPlayerMarks().set(i, x);
			}
		}
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.TARGET) {
				int x = board.getCurCenterRole(i).getRoleNum();
				player.getCenterMarks().set(i, x);
			}
		}
	}
}
