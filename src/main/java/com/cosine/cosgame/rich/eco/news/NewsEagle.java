package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsEagle extends News {
	public NewsEagle() {
		super();
		id = 22;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，最近是老鹰迁徙季，请广大市民留意自己的背包不要被老鹰抓走。本台专家建议市民根据老鹰的动态规划出行路线，这个有关背包的问题就完全No Problem，所以背包问题是NP完全问题。谢谢收听！";
		logDesc = "最近是老鹰迁徙季，请广大市民留意自己的背包不要被老鹰抓走";
	}
	
	public void effect() {
		super.effect();
		
		board.getNewsBuff().setDominantFate(10019);
		
	}
}
