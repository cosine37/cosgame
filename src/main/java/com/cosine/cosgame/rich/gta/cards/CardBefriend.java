package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Player;

public class CardBefriend extends Card {
	public CardBefriend() {
		super();
		id = 68;
		name = "远交近攻";
		desc = "指定一名其他玩家获得1张牌，若该玩家成功获得，你随机获得3张牌。消耗。";
		rarity = 1;
		playStyle = Consts.PLAYSTYLE_CHOOSEPLAYER;
	}
	
	public void play(int rawOptions) {
		int targetPlayerIndex = rawOptions%100;
		if (playable() && targetPlayerIndex>=0 && targetPlayerIndex<board.getPlayers().size()) {
			Player tp = board.getPlayers().get(targetPlayerIndex);
			
			board.setBroadcastImg("card/"+id);
			String targetName = tp.getName();
			if (targetName.contentEquals(player.getName())) {
				board.setBroadcastMsg(player.getName() + "对自己使用了" + name + "，但是没有获得任何牌。真以为能直接获得4张牌？" + player.getName() + "，汝怎可自作聪明！");
				board.getLogger().log("但是" + player.getName() + "指定了自己为目标，所以没有获得任何牌，真是自作聪明");
			} else if (tp.fullHand()){
				board.setBroadcastMsg(player.getName() + "对" + tp.getName() + "使用了" + name + "，但是" + tp.getName() + "手牌满了，所以" + player.getName() + "没有获得任何牌，这就尴尬了。");
				board.getLogger().log(player.getName() + "指定了" + tp.getName() + "为目标，但是" + tp.getName() + "手牌满了，所以" + player.getName() + "没有获得任何牌，这就尴尬了。");
			} else {
				boolean f;
				tp.addRandomCard();
				int x = 0;
				f = player.addRandomCard();
				if (f) x++;
				f = player.addRandomCard();
				if (f) x++;
				f = player.addRandomCard();
				if (f) x++;
				
				board.setBroadcastImg("card/"+id);
				board.getLogger().logGainCard(player, x);
				board.getLogger().logGainCard(tp, 1);
				if (x == 0) {
					board.setBroadcastMsg(player.getName() + "对" + tp.getName() + "使用了" + name + "，" + tp.getName() + "随机获得了1张牌，但是" + player.getName() + "手牌满了，没有获得任何牌，这就尴尬了。");
				} else {
					board.setBroadcastMsg(player.getName() + "对" + tp.getName() + "使用了" + name + "，" + tp.getName() + "随机获得了1张牌，" + player.getName() + "随机获得了" + x + "张牌。");
				}
			}
			
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		return defaultPlayable();
	}
}
