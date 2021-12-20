package com.cosine.cosgame.threechaodoms.base;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.Consts;

public class ZhouTai extends Card {
	public ZhouTai() {
		name = "周泰";
		courtesy = "幼平";
		img = "ZhouTai";
		title = "不惜軀明";
		faction = Consts.WU;
		
		desc = "领先标记-1,落后标记+1。";
	}
	
	public String getDescDisplay() {
		String ans = desc;
		if (board.getHanPos() > board.getWeiPos()) {
			ans = ans + " (王道-1,霸道+1)";
		} else if (board.getHanPos() < board.getWeiPos()) {
			ans = ans + " (王道+1,霸道-1)";
		} else {
			ans = ans + " (现在打出没有效果)";
		}
		return ans;
	}
	
	public void play(List<Integer> targets) {
		super.play(targets);
		if (board.getHanPos() > board.getWeiPos()) {
			board.moveHan(-1);
			board.moveWei(1);
		} else if (board.getHanPos() < board.getWeiPos()) {
			board.moveHan(1);
			board.moveWei(-1);
		} else {
		}
	}
}
