package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsGifts extends News {
	public NewsGifts() {
		super();
		id = 10;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，圣诞节即将来临，社区志愿者给所有玩家赠送礼物。本台专家猜测自己没收到礼物的原因可能是没洗袜子。谢谢收听，也祝大家节日快乐！";
		logDesc = "圣诞节即将来临，社区志愿者给所有玩家赠送礼物";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			Card c = new CardGift();
			p.addCard(c);
		}
	}
}
