package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardGift;

public class NewsCarTrain extends News {
	public NewsCarTrain() {
		super();
		id = 15;
		desc = "飞驰快讯为您报道：今日智能城市计划使用市民的行车记录训练自动驾驶模型，所有拥有载具的玩家获得$377数据贡献奖励。我是车神李飞轮，飞驰快讯，开得快，播得更快！";
		logDesc = "今日智能城市计划使用市民的行车记录训练自动驾驶模型，所有拥有载具的玩家获得$377数据贡献奖励";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.hasVehicle()) {
				p.addMoney(377);
			}
		}
	}
}
