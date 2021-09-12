package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class PoliceDetect extends Role{
	public PoliceDetect() {
		super();
		roleNum = 75;
		side = Consts.HUMAN;
		img = "r75";
		sequence = 577;
		name = "警探";
		choosePlayerNum = 2;
		hasNight = true;
		nightMsg.add("你的初始身份是 警探。");
		nightMsg.add("你可以查看0~2名玩家的身份。投票阶段你投的玩家获得的票数为2-x，x为你查看的身份数量。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 警探。");
		confirmedMsg.add("场上显示的身份为你当时查看的身份。投票阶段你投的玩家获得的票数为2-x，x为你查看的身份数量。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 警探，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份为你当时查看的身份。投票阶段你投的玩家获得的票数为2-x，x为你查看的身份数量。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 警探，你现在的身份可能已有变化。");
		votedMsg.add("场上显示的身份为你当时查看的身份。投票阶段你投的玩家获得的票数为2-x，x为你查看的身份数量。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			Manipulations.viewPlayerRole(player, p);
		}
		player.setQuestionChoosed(1);
	}
	
	public void useSkill(int t1, int t2) {
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			Manipulations.viewPlayerRole(player, p);
		}
		if (t2 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t2);
			Manipulations.viewPlayerRole(player, p);
		}
		player.setQuestionChoosed(2);
	}
	
	public int voteValue() {
		int ans = 2;
		if (player.getQuestionChoosed() == 1) {
			ans = 1;
		} else if (player.getQuestionChoosed() == 2) {
			ans = 0;
		}
		return ans;
	}
	
}
