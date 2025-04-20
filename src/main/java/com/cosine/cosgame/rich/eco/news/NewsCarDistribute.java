package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.Vehicle;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarDistribute extends News {
	public NewsCarDistribute() {
		super();
		id = 14;
		desc = "飞驰快讯为您报道：因直接报废过于可惜，滞销的奥拓将免费发放给没有载具的玩家。我是车神李飞轮，飞驰快讯，开得快，播得更快！";
		logDesc = "因直接报废过于可惜，滞销的奥拓将免费发放给没有载具的玩家";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (!p.hasVehicle()) {
				Vehicle v = Factory.genVehicle(28);
				p.receiveVehicle(v);
			}
		}
	}
}
