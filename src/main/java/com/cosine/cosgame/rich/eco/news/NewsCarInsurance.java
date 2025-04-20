package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarInsurance extends News {
	public NewsCarInsurance() {
		super();
		id = 13;
		desc = "飞驰快讯为您报道：友情提醒各位拥有载具的车主，别忘了交一年N度的500元交通强制险。你们忘了也没关系，银行已经帮你们注册了自动付款，是不是很贴心！我是车神李飞轮，飞驰快讯，开得快，付款得更快！";
		logDesc = "友情提醒各位拥有载具的车主，别忘了交一年N度的500元交通强制险";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.hasVehicle()) {
				p.loseMoney(500);
			}
		}
	}
}
