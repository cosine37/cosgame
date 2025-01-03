package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Windfall extends Event{
	public Windfall() {
		super();
		this.num = 11;
		this.img = "windfall";
		this.name = "天降横财";
		this.desc = "本轮游戏开始时，所有玩家获得8枚钱币。";
	}
	
	public void newRound() {
		List<Player> players = board.getPlayers();
		for (int i=0;i<players.size();i++) {
			players.get(i).addMoney(8);
		}
	}
}
