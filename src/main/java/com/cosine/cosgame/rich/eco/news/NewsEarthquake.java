package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsEarthquake extends News {
	public NewsEarthquake() {
		super();
		id = 18;
		desc = "";
		logDesc = "";
	}
	
	public void effect() {
		super.effect();
		
		Random rand = new Random();
		int targetArea = rand.nextInt(8)+1;
		
		String areaName = board.getMap().getAreaNames().get(targetArea);
		
		String desc = "欢迎来到地块八卦台，本台主播张嘴就来的砖姐提醒您：昨晚";
		desc = desc + areaName + "发生了轻微的地震，导致所有的房屋都下降一级。地是你的，瓜是大家的。";
		
		String logDesc = "昨晚" + areaName + "发生了轻微的地震，导致该区所有的房屋都下降一级";
		
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
					if (level > 0) {
						level = level-1;
						e.setLevel(level);
						board.getLogger().log(e.getName() + "的房屋下降一级");
					}
				}
			}
		}
		
	}
}
