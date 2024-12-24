package com.cosine.cosgame.oink.pope.cards;

import com.cosine.cosgame.oink.pope.Card;
import com.cosine.cosgame.oink.pope.Consts;
import com.cosine.cosgame.oink.pope.PopePlayer;

public class Witch extends Card{
	public Witch() {
		super();
		this.num = 5;
		type = Consts.CTYPE_TARGETSELF;
		this.name = "女巫";
		this.img = "Witch";
		this.desc = "指定一名在场玩家（可以是自己），该玩家弃置手牌并抽一张牌。";
	}
	
	public void onPlay(int target) {
		super.onPlay(target);
		logPlay(target);
		if (target == player.getIndex()) {
			player.discard();
			if (game.getDeck().size() == 0) {
				player.getHand().add(game.getSetAside());
				if (player.isActive()) {
					log(player.getName() + "将游戏开始时弃置的牌加入了手牌");
				}
			} else {
				player.draw();
				if (player.isActive()) {
					log(player.getName() + "抽了一张牌");
				}
			}
			player.setPhase(Consts.OFFTURN);
		} else if (target != -1) {
			PopePlayer tp = game.getPlayers().get(target);
			if (tp.isProtect()) {
				logProtect(tp);
				player.setPhase(Consts.OFFTURN);
			} else {
				String s = "正等待" + tp.getName() + "的确认。";
				player.setTargetedMsg(s);
				s = player.getName() + "对你打出了" + this.name + "，你的手牌将会被弃置并重抽一张。";
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
			tp.discard();
			if (game.getDeck().size() == 0) {
				tp.getHand().add(game.getSetAside());
				if (tp.isActive()) {
					log(tp.getName() + "将游戏开始时弃置的牌加入了手牌");
				}
				
			} else {
				tp.draw();
				if (tp.isActive()) {
					log(tp.getName() + "抽了一张牌");
				}
			}
			
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
