package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Status;

public class Love extends Status{
	public Love() {
		super();
		num = Consts.LOVE;
		name = "爱心";
		img = "s01";
	}
	
	public void vision() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getCurrentStatus().getNum() == Consts.LOVE) {
				player.getStatusMarks().set(i, p.getCurrentStatus().getNum());
			}
		}
	}
	
	public void votedOutHandle() {
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			if (player.getIndex() == i) {
				continue;
			}
			Player p = board.getPlayers().get(i);
			if (p.getCurrentStatus().getNum() == Consts.LOVE) {
				p.setVotedOut(true);
			}
		}
	}
}
