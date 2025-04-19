package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsHappyCity extends News {
	public NewsHappyCity() {
		super();
		id = 2;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，为响应快乐地球的号召，所有玩家的薪水增加$100，今天，你快乐了吗？谢谢收听！";
		logDesc = "为响应快乐地球的号召，所有玩家的薪水增加$100";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.addSalary(100);
		}
	}
}
