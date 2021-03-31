package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Redhat extends Role{
	public Redhat() {
		super();
		roleNum = 53;
		side = Consts.HUMAN;
		img = "r53";
		sequence = 402;
		name = "小红帽";
		nightMsg.add("你的初始身份是 小红帽。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 小红帽。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 小红帽");
		dayMsg.add("若有中央身份显示，则该身份为“狼”阵营玩家所看到的中央身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 小红帽。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		int i,j;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF) {
				for (j=0;j<p.getCenterMarks().size();j++) {
					int x = p.getCenterMarks().get(j);
					if (x != -1) {
						player.getCenterMarks().set(j, x);
					}
				}
			}
		}
	}
	
	
}
