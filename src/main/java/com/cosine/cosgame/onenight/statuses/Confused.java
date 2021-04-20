package com.cosine.cosgame.onenight.statuses;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Status;

public class Confused extends Status{
	public Confused() {
		super();
		num = Consts.CONFUSED;
		name = "煽惑";
		img = "s03";
	}
	
	public boolean win(boolean f) {
		boolean hasTeammate = false;
		boolean votedOutTeammate = false;
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getIndex() == player.getIndex()) {
				continue;
			}
			if (p.getSide() == player.getSide()) {
				hasTeammate = true;
				if (p.isVotedOut()) {
					votedOutTeammate = true;
				}
			}
		}
		
		if (hasTeammate == false) {
			return f;
		} else {
			return votedOutTeammate;
		}
		
	}
}
