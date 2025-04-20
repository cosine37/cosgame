package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarRace extends News {
	public NewsCarRace() {
		super();
		id = 11;
		desc = "飞驰快讯为您报道：今天是全民赛车狂欢节，所有拥有载具的玩家本回合行动的步数额外+3。我是车神李飞轮，飞驰快讯，开得快，播得更快！";
		logDesc = "今天是全民赛车狂欢节，所有拥有载具的玩家本回合行动的步数额外+3";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.hasVehicle()) {
				p.getBuff().setRollAdd(3);
				board.getLogger().log(p.getName() + " 本轮移动时额外移动 3 步");
			}
		}
	}
}
