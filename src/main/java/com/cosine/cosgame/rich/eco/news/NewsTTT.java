package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardTTT;

public class NewsTTT extends News {
	public NewsTTT() {
		super();
		id = 23;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天所有空杯子里的水发生轻微的核聚变反应，所有玩家每有一个空手牌位，获得一张烫烫烫。本台专家觉得水杯或可以替代托卡马克成为新的可控核聚变装置。谢谢收听！";
		logDesc = "昨天所有空杯子里的水发生轻微的核聚变反应，所有玩家每有一个空手牌位，获得一张烫烫烫";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			while (p.fullHand() == false) {
				Card c = new CardTTT();
				p.addCard(c);
			}
		}
	}
}
