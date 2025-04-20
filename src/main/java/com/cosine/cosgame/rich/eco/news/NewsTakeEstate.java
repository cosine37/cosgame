package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsTakeEstate extends News {
	public NewsTakeEstate() {
		super();
		id = 17;
		desc = "";
		logDesc = "";
	}
	
	public void effect() {
		super.effect();
		
		Random rand = new Random();
		int targetArea = rand.nextInt(8)+1;
		
		String areaName = board.getMap().getAreaNames().get(targetArea);
		
		String desc = "欢迎来到地块八卦台，本台主播张嘴就来的砖姐提醒您：本月将会举办睡眠奥运会，";
		desc = desc + areaName + "的地产将会被征用作为奥运村，当前拥有者会获得地产总价值的退款作为补偿。地不是你的，但瓜是大家的";
		
		String logDesc = "本月将会举办睡眠奥运会，" + areaName + "的地产将会被征用作为奥运村，当前拥有者会获得地产总价值的退款作为补偿。";
		
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
				if (e.getArea() == targetArea && e.isUnoccupied() == false) {
					Player owner = board.getPlayers().get(e.getOwnerId());
					
					board.getLogger().log(owner.getName() + " 失去了 " + e.getName() + " 的产权并获得了补偿 $" + e.totalCost());
					owner.addMoney(e.totalCost());
					e.removeOwner();
				}
			}
		}
		
	}
}
