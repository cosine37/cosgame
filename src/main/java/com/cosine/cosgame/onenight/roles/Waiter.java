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
	
	public void vision() {
		int numWolf = getNumWolf();
		String msg = "";
		if (numWolf == 0) {
			msg="本局游戏没有玩家的初始阵营为“狼”。";
		} else {
			msg="本局游戏有" + numWolf + "名玩家的初始阵营为“狼”。";
		}
		player.setMsg(msg);
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 服务员。");
		nightMsg.add(player.getMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 服务员。");
		confirmedMsg.add(player.getMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 服务员，你现在的身份可能已有变化。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		votedMsg.add("你的初始身份是 服务员，你现在的身份可能已有变化。");
		votedMsg.add(player.getMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
