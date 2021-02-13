package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Wolfdog extends Role{
	public Wolfdog() {
		super();
		roleNum = 44;
		side = Consts.WOLF;
		img = "r44";
		name = "狼狗";
		nightMsg.add("你的初始身份是 狼狗，你的狼队友认识你。");
		nightMsg.add("若你的身份牌被“人”阵营玩家看到，你的阵营会变为“人”。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 狼狗，你的狼队友认识你。");
		confirmedMsg.add("若你的身份牌被“人”阵营玩家看到，你的阵营会变为“人”。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 狼狗，你的狼队友认识你，你现在的身份可能已有变化。");
		dayMsg.add("若你的身份牌被“人”阵营玩家看到，你的阵营会变为“人”。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 狼狗，你的狼队友认识你，你现在的身份可能已有变化。");
		dayMsg.add("若你的身份牌被“人”阵营玩家看到，你的阵营会变为“人”。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void onView(Player viewer) {
		if (viewer.getInitialRole().getSide() == Consts.HUMAN) {
			side = Consts.HUMAN;
			img = "r4400";
		}
	}
	
	public String getDBStorageImg() {
		if (this.side == Consts.HUMAN) {
			return "r4400";
		} else if (this.side == Consts.WOLF) {
			return "r4401";
		} else {
			return "r44";
		}
	}
	
	public int getRoleNum() {
		if (this.side == Consts.HUMAN) {
			return 4400;
		} else if (this.side == Consts.WOLF) {
			return 4401;
		} else {
			return roleNum;
		}
	}
	
	public int getRoleNumToShow() {
		if (this.side == Consts.HUMAN) {
			return 4400;
		} else if (this.side == Consts.WOLF) {
			return 4401;
		} else {
			return roleNum;
		}
	}
	
	public String getDisplayImg() {
		if (this.side == Consts.HUMAN) {
			return "r4400";
		} else if (this.side == Consts.WOLF) {
			return "r4401";
		} else {
			return img;
		}
	}
	
	public String getFinalImg() {
		if (this.side == Consts.HUMAN) {
			return "r4400";
		} else if (this.side == Consts.WOLF) {
			return "r4401";
		} else {
			return img;
		}
	}
}
