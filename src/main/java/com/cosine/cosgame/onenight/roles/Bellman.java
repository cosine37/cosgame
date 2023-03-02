package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Bellman extends Role{
	public Bellman() {
		super();
		roleNum = 87;
		side = Consts.HUMAN;
		img = "r87";
		sequence = 587;
		name = "敲钟人";
		hasNight = true;
		
	}
	
	public void vision() {
		String msg = "出于某种原因，你不知道命运时针指向哪位玩家。";
		int x = player.getCurrentClockIndex();
		if (x>=0 && x<board.getPlayers().size()) {
			msg = "你行动时，命运时针指向" + board.getPlayers().get(x).getName()+"。";
		} else {
			
		}
		player.setMsg(msg);
	}
	
	public void visionPoisoned() {
		Random rand = new Random();
		int x = rand.nextInt(board.getPlayers().size());
		String msg = "你行动时，命运时针指向" + board.getPlayers().get(x).getName()+"。";
		player.setMsg(msg);
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 敲钟人。");
		nightMsg.add(player.getMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 敲钟人。");
		confirmedMsg.add(player.getMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 敲钟人，你现在的身份可能已有变化。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		votedMsg.add("你的初始身份是 敲钟人，你现在的身份可能已有变化。");
		votedMsg.add(player.getMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
