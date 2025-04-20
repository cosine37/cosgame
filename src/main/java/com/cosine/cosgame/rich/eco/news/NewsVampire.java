package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsVampire extends News {
	public NewsVampire() {
		super();
		id = 8;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，凌晨，一位奄奄一息的路人被送去医院抢救，暂时脱离生命危险，当事人声称自己曾被吸血老太婆袭击。本台专家提醒市民们注意安全，也呼吁市民们热血起来，早日烫死吸血老太婆。谢谢收听！";
		logDesc = "凌晨，一位奄奄一息的路人被送去医院抢救，暂时脱离生命危险，当事人声称自己曾被吸血老太婆袭击，请大家注意安全";
	}
	
	public void effect() {
		super.effect();
		
		board.getNewsBuff().setDominantFate(10018);
		
	}
}
