package com.cosine.cosgame.rich.gta.cards;

import java.util.List;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardAllRich extends Card {
	public CardAllRich() {
		super();
		id = 48;
		name = "均富卡";
		desc = "平分所有人的现金。消耗。";
		rarity = 3;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.getLogger().log("所有人的现金被平分了");
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了均富卡，平分了所有人的现金。");
			
			int total = 0;
			int i;
			List<Player> players = player.getBoard().getPlayers();
			for (i=0;i<players.size();i++) {
				total = total+players.get(i).getMoney();
			}
			int x = total/players.size();
			for (i=0;i<players.size();i++) {
				players.get(i).setMoney(x);
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
