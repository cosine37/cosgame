package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Sentinel extends Role{
	public Sentinel() {
		super();
		roleNum = 55;
		side = Consts.HUMAN;
		img = "r55";
		sequence = -100;
		name = "魔法警卫";
		choosePlayerNum = 1;
		hasDusk = true;
		duskMsg.add("你的身份是 魔法警卫。");
		duskMsg.add("你可以指定一名玩家，该玩家身份牌不能被查看、交换或改变。");
		duskMsg.add("点击确认结束你的黄昏阶段。");
		nightMsg.add("你的初始身份是 魔法警卫。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 魔法警卫。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 魔法警卫，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上有魔法守护标记的玩家是你指定的目标，该玩家身份牌不能被查看、交换或改变。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 魔法警卫，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeDuskSkill() {
		int i;
		System.out.println(player.getPlayerMarks().toString());
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				board.setSentinelIndex(i);
				player.getPlayerMarks().set(i, Consts.UNKNOWN);
				break;
			}
		}
	}
}
