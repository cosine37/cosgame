package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsPeachGarden extends News {
	public NewsPeachGarden() {
		super();
		id = 4;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天剧组在我市拍摄桃园结义，所有玩家回复1点生命值。本台刚住院的专家表示为什么不前天就拍摄。谢谢收听！";
		logDesc = "昨天剧组在我市拍摄桃园结义，所有玩家回复1点生命值";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.addHp(1);
		}
	}
}
