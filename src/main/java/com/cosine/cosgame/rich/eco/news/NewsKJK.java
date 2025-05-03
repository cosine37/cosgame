package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardKJK;

public class NewsKJK extends News {
	public NewsKJK() {
		super();
		id = 24;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，近日有大量古代遗物出土，但是遗物上的文字意义不明。在本台专家的建议下，遗物的样品已寄给每名玩家，希望民间有力量能破译古代文字。谢谢收听！";
		logDesc = "近日有大量古代遗物出土，但是遗物上的文字意义不明，遗物的样品已寄给每名玩家";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			Card c = new CardKJK();
			p.addCard(c);
		}
	}
}
