package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsArrows extends News {
	public NewsArrows() {
		super();
		id = 6;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天剧组在我市拍摄官渡之战时放出的箭误伤群众，所有玩家失去1点生命值。本台专家表示如果两千年前袁绍不一直放箭放箭，该惨剧完全可以避免。谢谢收听！";
		logDesc = "昨天剧组在我市拍摄官渡之战时放出的箭误伤群众，所有玩家失去1点生命值";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.loseHp(1);
		}
	}
}
