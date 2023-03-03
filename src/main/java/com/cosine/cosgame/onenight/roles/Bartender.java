package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Bartender extends Role{
	public Bartender() {
		super();
		roleNum = 94;
		side = Consts.HUMAN;
		img = "r94";
		sequence = 1100;
		name = "酒保";
		hasNight = true;
		nightMsg.add("你的初始身份是 酒保。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以知道一个成功发动技能的其他“人”阵营身份。");
		confirmedMsg.add("你的初始身份是 酒保。");
		confirmedMsg.add("天亮时你可以知道一个成功发动技能的其他“人”阵营身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		votedMsg.add("你的初始身份是 酒保。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		int i;
		List<Player> lp = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				continue;
			}
			if (p.getInitialRole().getSide() == Consts.HUMAN && p.getInitialRole().isHasNight() 
					&& p.isPoisoned() == false && p.isStoned() == false) {
				lp.add(p);
			}
		}
		String msg = "";
		if (lp.size() == 0) {
			msg = "除你之外，没有其他“人”阵营身份在夜晚或黎明阶段发动过技能。";
		} else {
			Random rand = new Random();
			int x = rand.nextInt(lp.size());
			String roleName = lp.get(x).getInitialRole().getName();
			msg = roleName + "的技能被成功发动过。";
		}
		player.setMsg(msg);
	}
	
	public void executeSkillPoisoned() {
		int i;
		List<Role> lr = new ArrayList<>();
		for (i=0;i<board.getRolesThisGame().size();i++) {
			Role r = board.getRolesThisGame().get(i);
			if (r.getSide() == Consts.HUMAN && r.getRoleNum() != Consts.BARTENDER) {
				lr.add(r);
			}
		}
		String msg = "";
		if (lr.size() == 0) {
			msg = "除你之外，没有其他“人”阵营身份在夜晚或黎明阶段发动过技能。";
		} else {
			Random rand = new Random();
			int x = rand.nextInt(lr.size());
			String roleName = lr.get(x).getName();
			msg = roleName + "的技能被成功发动过。";
		}
		player.setMsg(msg);
	}
	
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 酒保，你现在的身份可能已有变化。");
		dayMsg.add(player.getMsg());
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
}
