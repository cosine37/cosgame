package com.cosine.cosgame.rich.gta.cards;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.basicplaces.Estate;

public class CardDinosaur extends Card {
	public CardDinosaur() {
		super();
		id = 30;
		name = "恐龙卡";
		desc = "指定一个地块，拆除房屋，对该地块上的所有玩家造成2点伤害并摧毁载具。消耗。";
		rarity = 3;
		playStyle = Consts.PLAYSTYLE_CHOOSEGRID;
		attack = 2;
	}
	
	public void play(int rawOptions) {
		int targetPlaceIndex = rawOptions%100;
		if (playable() && targetPlaceIndex>=0 && targetPlaceIndex<board.getMap().getPlaces().size()) {
			Place place = board.getMap().getPlaces().get(targetPlaceIndex);
			
			board.setBroadcastImg("card/"+id);
			
			board.getLogger().log(player.getName() + " 召唤的恐龙对 " + place.getName() + " 进行了破坏");
			String broadcastMsg = player.getName()+ "使用了恐龙卡，召唤的恐龙对" + place.getName() + "进行了破坏";
			
			boolean f1 = false;
			if (place.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) place;
				if (e.getLevel() > 0) {
					board.getLogger().log(e.getName() + " 当地的住房被拆毁");
					broadcastMsg = broadcastMsg + "，当地的住房被拆毁";
					f1 = true;
					e.destroyHouse();
				} else {
					
				}
			} else {
				
			}
			
			int i,j;
			
			int x = getFinalAttack();
			
			String ts = "";
			boolean f2 = false;
			for (i=0;i<place.getPlayersOn().size();i++) {
				Player p = place.getPlayersOn().get(i);
				player.hurt(p, x);
				if (p.hasVehicle()) {
					p.loseVehicle();
				}
				if (i == 0) {
					ts = ts+p.getName();
				} else if (i==place.getPlayersOn().size()-1) {
					ts = ts+"和"+p.getName();
				} else {
					ts = ts+"、"+p.getName();
				}
				f2 = true;
			}
			ts = ts+"受到了" + x + "点伤害且失去载具"; 
			if (f2) {
				broadcastMsg = broadcastMsg+"，" + ts;
			} else {
				
			}
			
			if (f1 == false && f2 == false) {
				broadcastMsg = broadcastMsg+"，但是破坏了个寂寞。";
			} else {
				broadcastMsg = broadcastMsg+"。";
			}
			board.setBroadcastMsg(broadcastMsg);
			
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
