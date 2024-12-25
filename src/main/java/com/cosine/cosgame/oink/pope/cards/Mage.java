package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Mage extends Card{
	public Mage() {
		super();
		this.num = 7;
		type = Consts.CTYPE_TARGET;
		this.name = "法师";
		this.img = "Mage";
		this.desc = "与一名其他在场玩家交换手牌。";
		this.color = "Purple";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay(target);
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			if (tp.isProtect()) {
				logProtect(tp);
				player.setPhase(Consts.OFFTURN);
			} else {
				String s = "正等待" + tp.getName() + "的确认。";
				player.setTargetedMsg(s);
				s = player.getName() + "对你打出了" + this.name + "，其手牌将会与你的手牌交换。";
				tp.setTargetedMsg(s);
				tp.setPhase(Consts.TARGETED);
				player.setPhase(Consts.WAITING);
			}
		}
	}
	
	public void onTargetConfirm() {
		super.onTargetConfirm();
		int target = player.getTarget();
		if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			Card c1 = tp.getHand().get(0);
			Card c2 = player.getHand().get(0);
			tp.getHand().set(0, c2);
			player.getHand().set(0, c1);
			log(player.getName() + "和" + tp.getName() + "交换了各自的手牌");
			player.setPhase(Consts.OFFTURN);
			tp.setPhase(Consts.OFFTURN);
		}
	}
	
	public boolean canPlay() {
		if (player != null) {
			for (int i=0;i<player.getHand().size();i++) {
				Card c = player.getHand().get(i);
				if (c.getNum() == 8) return false;
			}
		}
		return true;
	}
	
}
