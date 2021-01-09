package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Watcher extends Role{
	public Watcher() {
		super();
		roleNum = 18;
		side = Consts.HUMAN;
		img = "r18";
		sequence = 950;
		name = "观者";
		nightMsg.add("你的初始身份是 观者。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到初始身份为预言家和见习预言家的玩家的最终身份。");
		confirmedMsg.add("你的初始身份是 观者。");
		confirmedMsg.add("天亮时你可以看到初始身份为预言家和见习预言家的玩家的最终身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 观者");
		dayMsg.add("场上显示的身份牌为初始身份是预言家和见习预言家的玩家的最终身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 观者。");
		votedMsg.add("场上显示的身份牌为初始身份是预言家和见习预言家的玩家的最终身份。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Role ir = board.getPlayers().get(i).getInitialRole();
			if (ir.getRoleNum() == Consts.SEER || ir.getRoleNum() == Consts.APPRENTICESEER) {
				Role cr = board.getPlayers().get(i).getCurrentRole();
				player.getPlayerMarks().set(i, cr.getRoleNum());
			}
		}
	}
}
