package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Monk extends Role{
	public Monk() {
		super();
		roleNum = 32;
		side = Consts.HUMAN;
		img = "r32";
		sequence = 0;
		name = "僧侣";
		nightMsg.add("你的初始身份是 僧侣。");
		nightMsg.add("你的身份牌不会被交换。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 僧侣。");
		confirmedMsg.add("你的身份牌不会被交换。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 僧侣，且未发生改变。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 僧侣，且未发生改变。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public boolean exchangable() {
		return false;
	}
}
