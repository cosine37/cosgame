package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Waiter extends Role{
	public Waiter() {
		super();
		roleNum = 78;
		side = Consts.HUMAN;
		img = "r78";
		sequence = 410;
		name = "服务员";
		hasNight = true;
		
	}
	
	int getNumWolf() {
		int ans = 0;
		for (int i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getSide() == Consts.WOLF) {
				ans++;
			}
		}
		return ans;
	}
	
	public String getMsg() {
		String ans = "";
		int numWolf = getNumWolf();
		if (numWolf == 0) {
			ans="本局游戏没有玩家的初始阵营为“狼”。";
		} else {
			ans="本局游戏有" + numWolf + "名玩家的初始阵营为“狼”。";
		}
		return ans;
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 服务员。");
		nightMsg.add(getMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 服务员。");
		confirmedMsg.add(getMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		dayMsg.add("你的初始身份是 服务员，你现在的身份可能已有变化。");
		dayMsg.add(getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		votedMsg.add("你的初始身份是 服务员，你现在的身份可能已有变化。");
		votedMsg.add(getMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
