package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;

public class CardTeleport extends Card {
	public CardTeleport() {
		super();
		id = 40;
		name = "传送卡";
		desc = "移动时无视步数且不会入狱，到达指定的地点（不触发经过效果）。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEGRID;
	}
	
	public void play(int rawOptions) {
		int targetPlaceIndex = rawOptions%100;
		if (playable() && targetPlaceIndex>=0 && targetPlaceIndex<board.getMap().getPlaces().size()) {
			player.getBuff().setTeleport(targetPlaceIndex);
			
			Place place = board.getMap().getPlaces().get(targetPlaceIndex);
			
			board.getLogger().log(player.getName() + " 将会传送到 " + place.getName());
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name + "，将会传送到" + place.getName() + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
