package com.cosine.cosgame.rich.eco.news;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;

public class NewsGust extends News {
	public NewsGust() {
		super();
		id = 7;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天我市刮起一阵微小的飓风，所有玩家丢弃1张随机手牌。本台专家大胆猜测，大家都丢弃卡牌，可能是因为大家都不喜欢卡牌。谢谢收听！";
		logDesc = "昨天我市刮起一阵微小的飓风，所有玩家丢弃1张随机手牌";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			Card c = p.sendRandom();
			if (c != null) {
				board.getLogger().log(p.getName() + " 失去了 " + c.getName());
				c.onThrow();
			}
		}
	}
}
