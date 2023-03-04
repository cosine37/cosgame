package com.cosine.cosgame.onenight.roles;

import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class JackOfAllTrades extends Role{
	public JackOfAllTrades() {
		super();
		roleNum = 97;
		side = Consts.HUMAN;
		img = "r97";
		sequence = 660;
		name = "万事通";
		choosePlayerNum = 1;
		chooseCenterNum = 2;
		hasNight = true;
		mandatory = true;
		canChooseBoth = true;
		nightMsg.add("你的初始身份是 万事通。");
		nightMsg.add("选择一名玩家和两张中央身份。选择的两张中央身份会被交换，天亮时你知道你选择的玩家是否中毒以及你行动时是否已被命运时针指向过。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 万事通。");
		confirmedMsg.add("你选择的两张中央身份会被交换，天亮时你知道你选择的玩家是否中毒以及你行动时是否已被命运时针指向过。");
		confirmedMsg.add("正等待其他玩家确认。");
		votedMsg.add("你的初始身份是 万事通，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1, int t2, int t3) {
		int ip = -1;
		int ic1 = -1;
		int ic2 = -1;
		if (t1 < board.getPlayers().size()) {
			ip = t1;
			ic1 = t2;
			ic2 = t3;
		} else if (t2 < board.getPlayers().size()) {
			ip = t2;
			ic1 = t1;
			ic2 = t3;
		} else if (t3 < board.getPlayers().size()) {
			ip = t3;
			ic1 = t1;
			ic2 = t2;
		}
		if (ip>=0 && ip < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.TARGET);
		}
		if (ic1>=100 && ic1<=102 && ic2>=100 && ic2<=102) {
			int x1 = ic1 - 100;
			int x2 = ic2 - 100;
			player.getCenterMarks().set(x1, Consts.EXCHANGE);
			player.getCenterMarks().set(x2, Consts.EXCHANGE);
		}
	}
	
	public void executeSkill() {
		String msg = "";
		int i;
		boolean pointed = false;
		for (i=0;i<player.getClockIndexes().size();i++) {
			if (player.getClockIndexes().get(i) == player.getIndex()) {
				pointed = true;
				break;
				
			}
		}
		if (pointed) {
			msg = msg + "你在行动之前（包括行动时）被命运时针指向过。";
		} else {
			msg = msg + "你在行动之前（包括行动时）没有被命运时针指向过。";
		}
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				if (p.isPoisoned()) {
					msg = msg + p.getName() + "天亮前中毒了。";
				} else {
					msg = msg + p.getName() + "天亮前没有中毒。";
				}
			}
		}
		int t1 = -1;
		int t2 = -1;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) == Consts.EXCHANGE) {
				if (t1 == -1) {
					t1 = i;
				} else {
					t2 = i;
				}
			}
		}
		if (t1 != -1 && t2 != -1) {
			Manipulations.swapCenterRoles(board, t1, t2);
		}
		player.setMsg(msg);
	}
	
	public void executeSkillPoisoned() {
		String msg = "";
		int i;
		Random rand = new Random();
		int t = rand.nextInt(10000);
		if (t%2 == 0) {
			msg = msg + "你在行动之前（包括行动时）被命运时针指向过。";
		} else {
			msg = msg + "你在行动之前（包括行动时）没有被命运时针指向过。";
		}
		for (i=0;i<player.getPlayerMarks().size();i++) {
			if (player.getPlayerMarks().get(i) == Consts.TARGET) {
				Player p = board.getPlayers().get(i);
				int x = rand.nextInt(9999);
				if (x%3 == 0) {
					msg = msg + p.getName() + "天亮前中毒了。";
				} else {
					msg = msg + p.getName() + "天亮前没有中毒。";
				}
			}
		}
		player.setMsg(msg);
	}
	
	public int voteValue() {
		return 0;
	}
	
	public void voteHandle() {
		super.voteHandle();
		int x = player.getVoteIndex();
		if (x>=0 && x<=board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			Player pp = p.prevPlayer();
			Player np = p.nextPlayer();
			pp.receiveVote(1);
			np.receiveVote(1);
		}
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 万事通，你现在的身份可能已有变化。");
		dayMsg.add("你选择的两张中央身份牌被交换过。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
}
