package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsStationCaveIn extends News {
	public NewsStationCaveIn() {
		super();
		id = 5;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天地铁隧道塌方，所有在地铁站的玩家失去3点生命值，且近期地铁站失去移动功能。本台专家建议昨天最好不要去地铁站，谢谢收听！";
		logDesc = "昨天地铁隧道塌方，所有在地铁站的玩家失去3点生命值，且近期地铁站失去移动功能";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			Place place = p.getPlace();
			if (place.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) place;
				if (e.getArea() == Consts.AREA_STATION) {
					board.getLogger().logLoseHp(p, 3);
					p.loseHp(3);
				}
			}
		}
		
		board.getNewsBuff().setNoStationMove(1);
		
	}
}
