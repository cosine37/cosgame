package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class PlagueDoctor extends Role{
	public PlagueDoctor() {
		super();
		roleNum = 86;
		side = Consts.HUMAN;
		img = "r86";
		sequence = 350;
		name = "鸟嘴医生";
		hasNight = true;
		canBePoisoned = false;
	}
	
	int getNumPoisoned() {
		int ans = 0;
		for (int i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.isPoisoned()) {
				ans++;
			}
		}
		return ans;
	}
	
	int getNumDelusional() {
		int ans = 0;
		for (int i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getRoleNum() == Consts.DELUSIONAL) {
				ans++;
			}
		}
		return ans;
	}
	
	public void vision() {
		int numPoisoned = getNumPoisoned();
		int numDelusional = getNumDelusional();
		String msg = "";
		if (numPoisoned == 0) {
			msg="夜晚阶段开始时没有玩家中毒；";
		} else {
			msg="夜晚阶段开始时有" + numPoisoned + "名玩家中毒；";
		}
		if (numDelusional == 0) {
			msg=msg+"没有玩家的初始身份是妄想者。";
		} else {
			msg=msg+"有玩家的初始身份是妄想者。";
		}
		player.setMsg(msg);
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 鸟嘴医生。");
		nightMsg.add(player.getMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 鸟嘴医生。");
		confirmedMsg.add(player.getMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 鸟嘴医生，你现在的身份可能已有变化。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		votedMsg.add("你的初始身份是 鸟嘴医生，你现在的身份可能已有变化。");
		votedMsg.add(player.getMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
