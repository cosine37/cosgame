package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsAngel extends News {
	public NewsAngel() {
		super();
		id = 20;
		desc = "";
		logDesc = "";
	}
	
	public void effect() {
		super.effect();
		
		Random rand = new Random();
		int targetArea = rand.nextInt(8)+1;
		
		String areaName = board.getMap().getAreaNames().get(targetArea);
		
		String desc = "欢迎来到地块八卦台，本台主播张嘴就来的砖姐提醒您：昨晚天使降临";
		desc = desc + areaName + "，在天使的祝福下该区所有的房屋都上升2级。地是你的，瓜是大家的。";
		
		String logDesc = "昨晚天使降临" + areaName + "，在天使的祝福下该区所有的房屋都上升2级";
		
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
					int level = e.getLevel();
					level = level+2;
					if (level > e.getMaxLevel()) level = e.getMaxLevel();
					e.setLevel(level);
					board.getLogger().log("地产 " + e.getName() + " 上升2级");
				}
			}
		}
		
	}
}
