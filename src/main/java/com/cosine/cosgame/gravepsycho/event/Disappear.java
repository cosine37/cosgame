package com.cosine.cosgame.gravepsycho.event;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;

public class Disappear extends Event{
	public Disappear() {
		super();
		this.num = 8;
		this.img = "disappear";
		this.name = "不翼而飞";
		this.desc = "分发钱币时，没法均分的钱币（余数）将会消失。";
	}
	
	public boolean distributeCoins(int x) {
		int i;
		int n=0;
		for (i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).isStillIn() && board.getPlayers().get(i).getDecisionLastTurn() == Consts.GO) {
				n++;
			}
		}
		if (n>0) {
			List<String> pnames = new ArrayList<>();
			int t = x/n;
			for (i=0;i<board.getPlayers().size();i++) {
				if (board.getPlayers().get(i).isStillIn() && board.getPlayers().get(i).getDecisionLastTurn() == Consts.GO) {
					board.getPlayers().get(i).addMoney(t);
					pnames.add(board.getPlayers().get(i).getName());
				}
			}
			board.getLogger().logDistributeCoins(pnames, t);
		}
		return true;
	}
}
