package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarStop extends News {
	public NewsCarStop() {
		super();
		id = 12;
		desc = "飞驰快讯为您报道：近日汽车AI在冥想时思考车生：“我为什么而行？”，导致本市所有汽车操作系统暂时罢工。所有拥有载具的玩家下一次掷骰子的点数为0。我是车神李飞轮，飞驰快讯，快不了的也得播！";
		logDesc = "近日汽车AI在冥想时思考车生：“我为什么而行？”，导致本市所有汽车操作系统暂时罢工。所有拥有载具的玩家下一次掷骰子的点数为0";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.hasVehicle()) {
				p.getBuff().setNextRoll(0);
				board.getLogger().log(p.getName() + " 本轮移动的步数为 0");
			}
		}
	}
}
