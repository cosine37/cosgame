package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Butterfly extends Role{
	public Butterfly() {
		super();
		roleNum = 46;
		side = Consts.HUMAN;
		img = "r46";
		sequence = 593;
		name = "花蝴蝶";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 花蝴蝶。");
		nightMsg.add("你可以将你的身份和一名其他玩家的身份交换，你也可以不交换。");
		nightMsg.add("若你选择交换，黎明之前你的身份与该玩家的身份将再次被交换。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，天亮时你可以看到你获得的身份牌。");
		confirmedMsg.add("你的初始身份是 花蝴蝶。");
		confirmedMsg.add("身份牌上拥有交换标记的玩家是你选择的交换对象，黎明之前你的身份与该玩家的身份将再次被交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 花蝴蝶，你现在的身份可能已有变化。");
		dayMsg.add("身份牌上拥有交换标记的玩家是你当时的交换对象。");
		dayMsg.add("你初始身份右侧的身份是你首次交换时获得的身份，黎明之前再次被交换。");
		dayMsg.add("若你初始身份右侧没有其他身份，说明你并没有发动你的技能。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 花蝴蝶，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				Player p = board.getPlayers().get(i);
				Manipulations.swapRoles(player, p);
				Manipulations.viewCurrentRole(player);
				break;
			}
		}
	}
	
	public void onDawnSkill() {
		int i;
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.EXCHANGE) {
				Player p = board.getPlayers().get(i);
				Manipulations.swapRoles(player, p);
				break;
			}
		}
	}
}
