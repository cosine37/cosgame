package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;

public class CardBuyEstate extends Card {
	public CardBuyEstate() {
		super();
		id = 43;
		name = "购地卡";
		desc = "购买一个未被购买的地产。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEGRID;
	}
	
	public void play(int rawOptions) {
		int targetPlaceIndex = rawOptions%100;
		if (playable() && targetPlaceIndex>=0 && targetPlaceIndex<board.getMap().getPlaces().size()) {
			boolean flag = false;
			Place place = board.getMap().getPlaces().get(targetPlaceIndex);
			if (place.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) place;
				
				if (e.getOwnerId() == -1) {
					flag =true;
					if (e.getCost() <= player.getMoney()) {
						board.getLogger().log(player.getName() + " 花费了 $" + e.getCost() + " 购买了地产 " +place.getName());
				
						board.setBroadcastImg("card/"+id);
						board.setBroadcastMsg(player.getName() + "花费了$" + e.getCost() + "购买了地产" +place.getName());
						
						e.setOwnerId(player.getIndex());
						player.loseMoney(e.getCost());
						
					} else {
						board.getLogger().log(player.getName() + " 因付不起 $" + e.getCost() + " 所以没有购买地产 " +place.getName());
				
						board.setBroadcastImg("card/"+id);
						board.setBroadcastMsg(player.getName() + "因付不起$" + e.getCost() + "所以无法购买地产" +place.getName() + "，这就尴尬了。");
					}
				}
				
			} else {
				
				
			}
			
			if (flag == false) {
				if (place != null) {
					board.getLogger().log(player.getName() + " 无法购买 " + place.getName());
					board.setBroadcastImg("card/"+id);
					board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法购买" + place.getName() + "，这就尴尬了。");
				}
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
