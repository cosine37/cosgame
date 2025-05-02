package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsPollution extends News {
	public NewsPollution() {
		super();
		id = 19;
		desc = "";
		logDesc = "";
	}
	
	public void effect() {
		super.effect();
		
		Random rand = new Random();
		int targetArea = rand.nextInt(8)+1;
		
		String areaName = board.getMap().getAreaNames().get(targetArea);
		
		String desc = "欢迎来到地块八卦台，本台主播张嘴就来的砖姐提醒您：昨晚";
		desc = desc + areaName + "的化粪池爆炸，导致该区臭名远扬，近期被勒令整改，期间不需要支付路费。地是你的，瓜是大家的。";
		
		String logDesc = "昨晚" + areaName + "的化粪池爆炸，导致该区臭名远扬，近期被勒令整改，期间不需要支付路费";
		
		board.getLogger().log(logDesc);
		board.setBroadcastImg("news/"+id);
		board.setBroadcastMsg(desc);
		
		board.removeLastSe();
		int seId = id*10000+board.getMap().getId()*100+targetArea;
		String seSrc = "/sound/Rich/news/" + seId + ".mp3";
		board.addSes(seSrc);
		board.setSesPlayer(Consts.SES_ALLPLAYERS);
		
		int i;
		for (i=0;i<board.getMap().getPlaces().size();i++) {
			Place p = board.getMap().getPlaces().get(i);
			if (p.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) p;
				if (e.getArea() == targetArea) {
					e.getPlaceBuff().setDisable(5);
				}
			}
		}
		
	}
}
