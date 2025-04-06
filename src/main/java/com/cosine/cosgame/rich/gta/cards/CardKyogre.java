package com.cosine.cosgame.rich.gta.cards;

import java.util.List;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardKyogre extends Card {
	public CardKyogre() {
		super();
		id = 31;
		name = "盖欧卡";
		desc = "对所有其他玩家造成2点伤害，摧毁载具且失去2张手牌。消耗。";
		rarity = 3;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			board.getLogger().log("所有其他玩家受到 2 点伤害，失去载具且失去 2 张随机手牌");
			
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "召唤的盖欧卡使用了根源波动，所有其他玩家受到2点伤害，失去载具且失去2张随机手牌。");
			
			int i;
			List<Player> players = player.getBoard().getPlayers();
			for (i=0;i<players.size();i++) {
				if (player.getIndex()!=players.get(i).getIndex()) {
					players.get(i).loseHp(2);
					players.get(i).loseVehicle();
					players.get(i).discardRandom(2);
				}
			}
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
