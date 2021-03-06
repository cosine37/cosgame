package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Witch extends Role{
	public Witch() {
		super();
		roleNum = 14;
		side = Consts.HUMAN;
		img = "r14";
		sequence = 650;
		name = "女巫";
		choosePlayerNum = 0;
		chooseCenterNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 女巫。");
		nightMsg.add("你可以选择一张中央身份牌查看，若如此做，你需将其与任何玩家（包括你）的身份牌交换");
		nightMsg.add("如果你不想查看，点击确定结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 女巫。");
		confirmedMsg.add("场上显示的中央身份牌是你发动你技能时查看的身份牌，身份牌上拥有交换标记的玩家是你选择的交换对象。");
		confirmedMsg.add("若你没有发动技能，则不显示。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 女巫，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你交换给当时选择玩家的身份牌，可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 女巫，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 >= 100 && t1 <= 102) {
			Manipulations.peekCenterRole(player,board,t1);
		} else if (t1 < board.getPlayers().size()) {
			player.getPlayerMarks().set(t1, Consts.EXCHANGE);
		}
	}
	
	public boolean canConfirm(List<Integer> targets) {
		if (targets.size() == 0) {
			return true;
		}
		int x = targets.get(0);
		if (x >= 100 && x <= 102) {
			return false;
		} else {
			return true;
		}
	}
	
	public void executeSkill() {
		int ic = -1;
		int ip = -1;
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) != Consts.UNKNOWN) {
				ic = i;
				break;
			}
		}
		if (ic != -1) {
			for (i=0;i<player.getPlayerMarks().size();i++) {
				if (player.getPlayerMarks().get(i) != Consts.UNKNOWN) {
					ip = i;
					break;
				}
			}
			if (ip != -1) {
				Player p = board.getPlayers().get(ip);
				Manipulations.swapCenterRole(p, board, ic);
				player.getCenterMarks().set(ic, Consts.EXCHANGE);
				Manipulations.viewPlayerRole(player,p);
			} else {
				Manipulations.swapCenterRole(player, board, ic);
				player.getCenterMarks().set(ic, Consts.EXCHANGE);
				Manipulations.viewCurrentRole(player);
			}
			
		}
	}
	
	public int getChoosePlayerNum() {
		int ic = -1;
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) != Consts.UNKNOWN) {
				ic = i;
				break;
			}
		}
		if (ic == -1) {
			choosePlayerNum = 0;
		} else {
			choosePlayerNum = 1;
		}
		return choosePlayerNum;
	}
	
	public int getChooseCenterNum() {
		int ic = -1;
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) != Consts.UNKNOWN) {
				ic = i;
				break;
			}
		}
		if (ic == -1) {
			chooseCenterNum = 1;
		} else {
			chooseCenterNum = 0;
		}
		return chooseCenterNum;
	}
	
	public List<String> getNightMsg() {
		int ic = -1;
		int i;
		for (i=0;i<player.getCenterMarks().size();i++) {
			if (player.getCenterMarks().get(i) != Consts.UNKNOWN) {
				ic = i;
				break;
			}
		}
		if (ic != -1) {
			nightMsg = new ArrayList<>();
			nightMsg.add("你的初始身份是 女巫。");
			nightMsg.add("你查看的身份牌已显示，请选择一名玩家的身份牌与之交换， 若不选择，则视为和你自己的身份牌交换。");
			nightMsg.add("选择交换玩家后点击确定结束你的夜晚阶段。");
		}
		return nightMsg;
	}
}
