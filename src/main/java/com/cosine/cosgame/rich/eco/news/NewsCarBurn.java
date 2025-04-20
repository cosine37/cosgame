package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarBurn extends News {
	public NewsCarBurn() {
		super();
		id = 16;
		desc = "飞驰快讯为您报道：连日高温，导致全城汽车物理意义上的燃起来了，所有拥有载具的玩家失去载具和3点生命值。我是车神李飞轮，飞驰快讯，开得快，燃得更快！";
		logDesc = "连日高温，导致全城汽车物理意义上的燃起来了，所有拥有载具的玩家失去载具和3点生命值";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.hasVehicle()) {
				p.loseHp(3);
				p.loseVehicle();
				
				board.getLogger().log(p.getName() + "失去 3 点生命值并失去载具");
			}
		}
	}
}
