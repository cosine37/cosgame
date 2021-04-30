package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Gravekeeper extends Role{
	public Gravekeeper() {
		super();
		roleNum = 47;
		side = Consts.HUMAN;
		img = "r47";
		sequence = 920;
		name = "守墓者";
		hasNight = true;
		nightMsg.add("你的初始身份是 守墓者。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 守墓者。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 守墓者，若条件允许，你会看到一张不为变色狼的“狼”阵营中央身份。");
		dayMsg.add("若你没有看到任何中央身份，则中央身份中没有除变色狼之外的“狼”阵营身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 守墓者。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		List<Role> crs = new ArrayList<>();
		List<Integer> cis = new ArrayList<>();
		for (int i=0;i<board.getCenterRoles().size();i++) {
			Role r = board.getCurCenterRole(i);
			cis.add(i);
			crs.add(r);
		}
		while (crs.size() > 0) {
			Random rand = new Random();
			int x = rand.nextInt(crs.size());
			Role r = crs.remove(x);
			int y = cis.remove(x);
			if (r.getSide() == Consts.WOLF && r.getRoleNum() != Consts.WEREMELEON) {
				player.getCenterMarks().set(y, r.getRoleNum());
				break;
			}
		}
	}
	
	
}
