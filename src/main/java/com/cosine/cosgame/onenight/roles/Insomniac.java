package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Manipulations;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Insomniac extends Role{
	public Insomniac() {
		super();
		roleNum = 6;
		side = Consts.HUMAN;
		img = "r06";
		sequence = 900;
		name = "失眠者";
		nightMsg.add("你的初始身份是 失眠者。");
		nightMsg.add("点击确认结束你的夜晚阶段，天亮时你可以看到你的最终身份。");
		confirmedMsg.add("你的初始身份是 失眠者。");
		confirmedMsg.add("天亮时你可以看到你的最终身份。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 失眠者，你初始身份右侧的身份是你的最终身份。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 失眠者，你初始身份右侧的身份是你的最终身份。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void executeSkill() {
		Manipulations.viewCurrentRole(player);
	}
	
	
}
