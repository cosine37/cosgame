package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class VillageHead extends Role{
	public VillageHead() {
		super();
		roleNum = 29;
		side = Consts.HUMAN;
		img = "r29";
		sequence = 1099;
		name = "村长";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 村长。");
		nightMsg.add("指定一名玩家为初始发言玩家。若不指定，则你为初始发言玩家。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 村长。");
		confirmedMsg.add("身份牌上拥有标记的玩家是初始发言玩家。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 村长，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有标记的玩家是你当时的选择对象。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 村长，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		int x = player.getIndex();
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				x = i;
				break;
			}
		}
		board.setFirstPlayerIndex(x);
	}
	
	
}
