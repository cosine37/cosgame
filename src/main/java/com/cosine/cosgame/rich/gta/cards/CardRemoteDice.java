package com.cosine.cosgame.rich.gta.cards;

import java.util.ArrayList;
import java.util.Arrays;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardRemoteDice extends Card {
	public CardRemoteDice() {
		super();
		id = 7;
		name = "遥控骰子";
		desc = "指定你下一次掷骰子的点数，范围为1~6。消耗。";
		playStyle = Consts.PLAYSTYLE_CHOOSEOPTION;
		options = new ArrayList<>(Arrays.asList("1点","2点","3点","4点","5点","6点"));
		rarity = 1;
	}
	
	public void play(int rawOptions) {
		if (playable()) {
			int result = rawOptions+1;
			player.getBuff().setNextRoll(result);
			
			board.getLogger().logSetDiceResult(player, result);
			board.setBroadcastImg("card/"+id);
			board.setBroadcastMsg(player.getName() + "使用了" + name +"，指定下一次掷骰子的点数为" + result + "。");
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() || player.isInWard()) return false;
		if (player.getPhase() != Consts.PHASE_OFFTURN) return true;
		return false;
	}
}
