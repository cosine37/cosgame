package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsHacker extends News {
	public NewsHacker() {
		super();
		id = 21;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，近期有不少市民反应银行卡被盗刷，请大家小心黑客。本台舍己为人的专家呼吁所有市民都应该把银行卡交给他，这样只有他的银行卡会被盗刷了。谢谢收听！";
		logDesc = "近期有不少市民反应银行卡被盗刷，请大家小心黑客";
	}
	
	public void effect() {
		super.effect();
		
		board.getNewsBuff().setDominantFate(11);
		
	}
}
