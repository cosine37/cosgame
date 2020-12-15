package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Werewolf extends Role{
	public Werewolf() {
		super();
		roleNum = 1;
		side = Consts.WOLF;
		img = "r01";
		sequence = 200;
		name = "狼人";
		nightMsg.add("你的初始身份是 狼人。");
		nightMsg.add("场上显示身份牌的玩家初始身份和你同一阵营。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		dayMsg.add("你的初始身份是 狼人，你现在的身份可能已有变化。");
		dayMsg.add("场上显示身份牌的玩家初始身份和你同一阵营，但当前身份可能已有变化。");
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getInitialRole().getRoleNum() == Consts.WEREWOLF) {
				player.getPlayerMarks().set(i, 1);
			}
		}
	}
}
