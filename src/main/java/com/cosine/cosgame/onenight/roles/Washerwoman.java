package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Washerwoman extends Role{
	public Washerwoman() {
		super();
		roleNum = 79;
		side = Consts.HUMAN;
		img = "r79";
		sequence = 405;
		name = "洗衣妇";
		hasNight = true;
		
	}
	
	public void vision() {
		int i;
		List<Player> lp1 = new ArrayList<>();
		List<Player> lp2 = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getName().contentEquals(player.getName())) {
				continue;
			}
			lp1.add(board.getPlayers().get(i));
			lp2.add(board.getPlayers().get(i));
		}
		Player p1 = null;
		Player p2 = null;
		Role tr = null;
		while (lp1.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(lp1.size());
			if (lp1.get(x).getCurrentRole().getSide() == Consts.HUMAN) {
				p1 = lp1.get(x);
				tr = p1.getCurrentRole();
				break;
			} else {
				lp1.remove(x);
			}
		}
		String msg = "";
		if (p1 != null) {
			while (lp2.size()>0) {
				Random rand = new Random();
				int x = rand.nextInt(lp1.size());
				if (lp2.get(x).getName().contentEquals(p1.getName())) {
					lp2.remove(x);
				} else {
					p2 = lp2.get(x);
					break;
				}
			}
			Random rand = new Random();
			int x = rand.nextInt(10000);
			if (x%2 == 0) {
				msg = p1.getName() + "和" + p2.getName();
			} else {
				msg = p2.getName() + "和" + p1.getName();
			}
			msg = msg + "中有一名玩家的初始身份是" + tr.getName() + "。";
		} else {
			msg = "出于某些原因，你无法获得任何信息。";
		}
		player.setMsg(msg);
	}
	
	public List<String> getNightMsg() {
		nightMsg.add("你的初始身份是 洗衣妇。");
		nightMsg.add(player.getMsg());
		nightMsg.add("点击确认结束夜晚阶段");
		return nightMsg;
	}
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 洗衣妇。");
		confirmedMsg.add(player.getMsg());
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 洗衣妇，你现在的身份可能已有变化。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		votedMsg.add("你的初始身份是 洗衣妇，你现在的身份可能已有变化。");
		votedMsg.add(player.getMsg());
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
