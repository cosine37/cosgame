package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Knight extends Card{
	public Knight() {
		super();
		this.num = 3;
		type = Consts.CTYPE_TARGET;
		this.name = "骑士";
		this.img = "Knight";
		this.desc = "与一名其他在场玩家秘密比较手牌声望，声望小的玩家出局，相同则无人出局。";
		this.color = "OrangeRed";
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
				String s = "你与" + tp.getName() + "手牌声望更小的玩家将出局，正等待" + tp.getName() + "的确认。";
				player.setTargetedMsg(s);
				s = player.getName() + "对你打出了" + this.name + "，手牌声望更小的玩家将出局。";
				tp.setTargetedMsg(s);
				player.addResolve("你的手牌", player.getHand().get(0));
				player.addResolve(tp.getName() + "的手牌", tp.getHand().get(0));
				tp.cleanResolve();
				tp.addResolve(player.getName()+"的手牌", player.getHand().get(0));
				tp.addResolve("你的手牌", tp.getHand().get(0));
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
			if (c1.getNum() > c2.getNum()) {
				log("但是，" + player.getName() + "反而被" + tp.getName() + "击败，这就尴尬了。。。");
				player.inactivate();
			} else if (c2.getNum() > c1.getNum()) {
				log(player.getName() + "在决斗中击败了" + tp.getName());
				tp.inactivate();
			} else {
				log("两人打得难解难分，不分胜负");
			}
			player.setPhase(Consts.OFFTURN);
			tp.setPhase(Consts.OFFTURN);
		}
	}
	
}
