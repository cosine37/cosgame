package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Idiot extends Role{
	public Idiot() {
		super();
		roleNum = 17;
		side = Consts.HUMAN;
		img = "r17";
		sequence = 750;
		name = "白痴";
		hasNight = true;
		nightMsg.add("你的初始身份是 白痴。");
		nightMsg.add("你可以选择将除你以外的玩家的身份左移一顺位，或右移一顺位，或不移动");
		nightMsg.add("点击相应的按钮结束夜晚阶段");
	}
	
	public void useSkill(int t1) {
		player.getPlayerMarks().set(player.getIndex(), t1);
	}
	
	public void executeSkill() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		int i;
		List<Role> curRoles = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			curRoles.add(board.getPlayers().get(i).getCurrentRole());
		}
		for (i=0;i<board.getPlayers().size();i++) {
			if (i==x) continue;
			Role r = board.getPlayers().get(i).getCurrentRole();
			if (!r.exchangable()) {
				return;
			}
		}
		if (y == 0) {
			
		} else if (y == 1) {
			int startPlayer = 0;
			if (player.getIndex() == startPlayer) {
				startPlayer = 1;
			}
			int t = startPlayer;
			int k = -1;
			while (k!=startPlayer) {
				Role r = curRoles.get(t);
				r.onExchange();
				k = t-1;
				if (k<0) {
					k = k+board.getPlayers().size();
				}
				if (k == player.getIndex()) {
					k = k-1;
					if (k<0) {
						k = k+board.getPlayers().size();
					}
				}
				board.getPlayers().get(k).addRole(r);
				t = k;
			}
		} else if (y == 2) {
			int startPlayer = 0;
			if (player.getIndex() == startPlayer) {
				startPlayer = 1;
			}
			int t = startPlayer;
			int k = -1;
			while (k!=startPlayer) {
				Role r = curRoles.get(t);
				r.onExchange();
				k = t+1;
				if (k>=board.getPlayers().size()) {
					k = k-board.getPlayers().size();
				}
				if (k == player.getIndex()) {
					k = k+1;
					if (k>=board.getPlayers().size()) {
						k = k-board.getPlayers().size();
					}
				}
				board.getPlayers().get(k).addRole(r);
				t = k;
			}
		}
	}
	
	public List<String> getConfirmedMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		confirmedMsg.add("你的初始身份是 白痴。");
		if (y == 0) {
			confirmedMsg.add("你没有交换任何玩家的身份。");
		} else if (y == 1) {
			confirmedMsg.add("你将除你以外的所有玩家身份都左移了一顺位。");
		} else if (y == 2) {
			confirmedMsg.add("你将除你以外的所有玩家身份都右移了一顺位。");
		}
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		dayMsg.add("你的初始身份是 白痴，你现在的身份可能已有变化。");
		if (y == 0) {
			dayMsg.add("你没有交换任何玩家的身份。");
		} else if (y == 1) {
			dayMsg.add("你将除你以外的所有玩家身份都左移了一顺位。");
		} else if (y == 2) {
			dayMsg.add("你将除你以外的所有玩家身份都右移了一顺位。");
		}
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
	public List<String> getVotedMsg() {
		int x = player.getIndex();
		int y = player.getPlayerMarks().get(x);
		votedMsg.add("你的初始身份是 白痴，你现在的身份可能已有变化。");
		if (y == 0) {
			votedMsg.add("你没有交换任何玩家的身份。");
		} else if (y == 1) {
			votedMsg.add("你将除你以外的所有玩家身份都左移了一顺位。");
		} else if (y == 2) {
			votedMsg.add("你将除你以外的所有玩家身份都右移了一顺位。");
		}
		votedMsg.add("你已投票，正等待其他玩家投票。");
		return votedMsg;
	}
	
}
