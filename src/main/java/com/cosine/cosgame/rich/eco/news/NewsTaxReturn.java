package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsTaxReturn extends News {
	public NewsTaxReturn() {
		super();
		id = 1;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，税务局发现去年的所得税多收了10%，每名玩家获得等同于薪水10%的退税，退款已打到所有玩家的账户。谢谢收听！";
		logDesc = "税务局发现去年的所得税多收了10%，每名玩家获得等同于薪水10%的退税";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.addMoney(p.getSalary()/10);
		}
	}
}
