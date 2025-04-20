package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsPainCity extends News {
	public NewsPainCity() {
		super();
		id = 3;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，由于市民过于开心，连监狱中都能传来阵阵笑声，为响应忆苦思甜，所有玩家的薪水降低$200，今天，你痛苦了吗？谢谢收听！";
		logDesc = "由于市民过于开心，连监狱中都能传来阵阵笑声，为响应忆苦思甜，所有玩家的薪水降低$200";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.addSalary(-200);
		}
	}
}
