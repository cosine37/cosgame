package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsFrozenRain extends News {
	public NewsFrozenRain() {
		super();
		id = 25;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨夜忽下冻雨，导致路面结冰，所有玩家本回合的步数+3。本台专家提出可以用冰块造出更快的载具，但是飞驰快讯的车神李飞轮表示不敢苟同，谢谢收听！";
		logDesc = "昨夜忽下冻雨，导致路面结冰，所有玩家本回合的步数+3";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.getBuff().setRollAdd(3);
		}
	}
}
