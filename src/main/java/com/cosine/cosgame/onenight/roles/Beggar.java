package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Beggar extends Role{
	public Beggar() {
		super();
		roleNum = 28;
		side = Consts.HUMAN;
		img = "r28";
		sequence = 430;
		name = "乞丐";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 乞丐。");
		nightMsg.add("你可以指定一名玩家，该玩家天亮时知道你的初始身份。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 乞丐。");
		confirmedMsg.add("身份牌上拥有标记的玩家是你指定的玩家，该玩家天亮时知道你的初始身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 乞丐，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时指定的对象，该玩家知道你的初始身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 乞丐，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				p.setBeggarIndex(player.getIndex());
				p.getPlayerMarks().set(player.getIndex(), roleNum);
			}
		}
	}
	
}
