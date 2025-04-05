package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class CardRobotWorker extends Card {
	public CardRobotWorker() {
		super();
		id = 42;
		name = "机器工人";
		desc = "将指定的已被购买的地产升一级。消耗。";
		rarity = 2;
		playStyle = Consts.PLAYSTYLE_CHOOSEGRID;
	}
	
	public void play(int rawOptions) {
		int targetPlaceIndex = rawOptions%100;
		if (playable() && targetPlaceIndex>=0 && targetPlaceIndex<board.getMap().getPlaces().size()) {
			Place place = board.getMap().getPlaces().get(targetPlaceIndex);
			boolean f = changeEstateLevel(place,1);
			if (f) {
				board.getLogger().log(player.getName() + " 升级了 " + place.getName());
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "使用了" + name + "升级了" + place.getName() + "。");
			} else {
				if (player.getPlace() != null) {
					board.getLogger().log(player.getName() + " 无法升级 " + place.getName());
					board.setBroadcastImg("card/"+id);
					board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法升级" + place.getName() + "，这就尴尬了。");
				}
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
