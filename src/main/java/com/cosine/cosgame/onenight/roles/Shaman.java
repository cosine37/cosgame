package com.cosine.cosgame.onenight.roles;

import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Shaman extends Role{
	public Shaman() {
		super();
		roleNum = 83;
		side = Consts.HUMAN;
		img = "r83";
		sequence = 560;
		name = "萨满";
		//choosePlayerNum = 1;
		//chooseCenterNum = 2;
		hasNight = true;
		mandatory = true;
		confirmedMsg.add("你的初始身份是 萨满。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("若你当时已中毒，你查看的是玩家的身份且保真。否则你查看的是中央身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 萨满，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时所看到的身份牌，可能已有变化。");
		dayMsg.add("若你当时已中毒，你查看的是玩家的身份且保真。否则你查看的是中央身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 萨满，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			Player p = board.getPlayers().get(t1);
			Manipulations.viewPlayerRole(player, p);
		}
	}
	
	public int getChoosePlayerNum() {
		if (player.isPoisoned()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getChooseCenterNum() {
		if (player.isPoisoned()) {
			return 0;
		} else {
			return 2;
		}
	}
	
	public void useSkill(int t1, int t2) {
		Manipulations.viewCenterRoles(player, board, t1, t2);
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 萨满。");
		if (player.isPoisoned()) {
			nightMsg.add("你中毒了，选择一名其他玩家的身份牌查看，你查看的信息保真。");
		} else {
			nightMsg.add("你没有中毒，选择两张中央身份牌查看。");
		}
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	
}
