package com.cosine.cosgame.rich.gta.cards;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Consts;

public class CardRelease extends Card {
	public CardRelease() {
		super();
		id = 10;
		name = "出狱卡";
		desc = "在监狱内时可使用，立即出狱并清空通缉值，不需要支付保释费。消耗。";
		rarity = 0;
	}
	
	public void play(int rawOptions) {
		if (player.isInJail()) {
			player.outOfJail();
			
			board.setBroadcastImg("card/"+id);
			if (board.getSettings().getUseGTA() == 1) {
				player.setStar(0);
				board.getLogger().log(player.getName() + " 被释放出狱并清空了通缉值");
				board.setBroadcastMsg(player.getName() + "使用了出狱卡，被释放出狱且清空了通缉值。");
			} else {
				board.getLogger().log(player.getName() + " 被释放出狱");
				board.setBroadcastMsg(player.getName() + "使用了出狱卡，被释放出狱。");
			}
			
			
		}
		exhaust = true;
	}
	
	public boolean playable() {
		if (player.isInJail() && player.getPhase() == Consts.PHASE_ROLL) {
			return true;
		}
		return false;
	}
	
	public String getDesc() {
		if (board != null && board.getSettings().getUseGTA() == 1) {
			return "在监狱内时可使用，立即出狱并清空通缉值，不需要支付保释费。消耗。";
		} else {
			return "立即出狱，不需要支付保释费。消耗。";
		}
	}
	
	
}
