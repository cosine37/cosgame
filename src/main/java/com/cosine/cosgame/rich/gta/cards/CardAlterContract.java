package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class CardAlterContract extends Card {
	public CardAlterContract() {
		super();
		id = 39;
		name = "非法过户";
		desc = "将指定的地产据为己有，若成功发动，+2starP。消耗。";
		rarity = 3;
		playStyle = Consts.PLAYSTYLE_CHOOSEGRID;
	}
	
	public void play(int rawOptions) {
		int targetPlaceIndex = rawOptions%100;
		if (playable() && targetPlaceIndex>=0 && targetPlaceIndex<board.getMap().getPlaces().size()) {
			boolean flag = false;
			Place place = board.getMap().getPlaces().get(targetPlaceIndex);
			flag = changeEstateOwner(place, player);
			if (flag) {
				
				board.getLogger().log(player.getName() + " 强占了 " + place.getName() + " 并获得了 2 点通缉值");
				
				board.setBroadcastImg("card/"+id);
				board.setBroadcastMsg(player.getName() + "使用了" + name + "，强占了" + place.getName() + "并获得了2点通缉值。");
				
				player.addStar(2);
				
			} else {
				if (place != null) {
					board.getLogger().log(player.getName() + " 无法强占 " + place.getName());
					board.setBroadcastImg("card/"+id);
					board.setBroadcastMsg(player.getName() + "使用了" + name + "，但是无法强占" + place.getName() + "，这就尴尬了。");
				}
				
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
